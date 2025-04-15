package com.ai.ml.core.nlp.driver;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.*;

import java.util.List;
import java.util.Properties;

public class GrammarCheckExample {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
    props.setProperty("tokenize.language", "en");
    props.setProperty("ssplit.eolonly", "true");

    // Set up the Stanford CoreNLP pipeline with sentence splitting, tokenization, and parsing
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Create a CoreDocument object with a sample text
    String text = "This are a bad example."; // Example sentence with a grammar error
    CoreDocument document = new CoreDocument(text);

    // Annotate the document (this processes the text)
    pipeline.annotate(document);

    System.out.println("Annotated text:");
    for (CoreMap sentence : document.annotation().get(CoreAnnotations.SentencesAnnotation.class)) {
      System.out.println("Sentence: " + sentence.get(TextAnnotation.class));

      // Print word-level annotations (e.g., POS tags, NER)
      for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
        String word = token.get(TextAnnotation.class);
        String pos = token.get(PartOfSpeechAnnotation.class);
        String ner = token.get(NamedEntityTagAnnotation.class);
        System.out.println("Word: " + word + ", POS: " + pos + ", NER: " + ner);
      }
    }


    // Print out the sentence and parse tree (syntax tree)
    for (CoreSentence sentence : document.sentences()) {

      System.out.println("Sentence: " + sentence.text());
      System.out.println("POS Tags:");
      for (CoreLabel token : sentence.tokens()) {
        System.out.println(token.word() + " -> " + token.get(PartOfSpeechAnnotation.class));
       // System.out.println(token.word() + " -> " + token.get(CoreAnnotations.SentencesAnnotation.class));
      }
      /*
      Tree parseTree = sentence.constituencyParse();
      System.out.println("parseTree.labels();- " + parseTree.yieldWords());*/
      // Get and print the constituency parse tree
      Tree parseTree = sentence.constituencyParse();
      if (parseTree != null) {
        System.out.println("Parse Tree:");
        parseTree.printLocalTree();
      } else {
        System.out.println("Parse tree is null.");
      }

      // Check subject-verb agreement (basic example)
      // We assume that we need to check if the subject ("She") and verb ("go") agree in number
      List<CoreLabel> tokens = sentence.tokens();
      if (tokens.size() > 1) {
        String subject = tokens.get(0).word();  // The first word (assuming it's the subject)
        String verb = tokens.get(1).word();     // The second word (assuming it's the verb)

        if (subject.equals("She") && verb.equals("go")) {
          System.out.println("Grammatical issue: Subject-verb agreement error. 'She' should be followed by 'goes'.");
        }
      }
      for (CoreLabel token : sentence.tokens()) {
        String word = token.word();
        String ne = token.get(NamedEntityTagAnnotation.class);
        System.out.println(word + " -> " + ne);
      }
    }
  }
}

