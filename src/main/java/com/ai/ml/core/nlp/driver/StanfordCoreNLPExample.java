package com.ai.ml.core.nlp.driver;

import com.ai.ml.core.nlp.ExtractEntity;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class StanfordCoreNLPExample {

  public static void main(String[] args) {
    // Set up the pipeline
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Create a document object
    String text = "John was born in Hawaii amd his email id john1@aiextract.com";
    CoreDocument document = new CoreDocument(text);

    // Annotate the document
    pipeline.annotate(document);

    System.out.println("start now");
        // Print out the tokens and named entities
    for (CoreLabel token : document.tokens()) {
      //System.out.println("value of NamedEntityTagAnnotation::"+ token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
      if (!token.get(CoreAnnotations.NamedEntityTagAnnotation.class).equalsIgnoreCase("O"))
        System.out.println(token.word() + " - " + token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
    }

    ExtractEntity entity = new ExtractEntity();
    Set entityValue = new HashSet();
    entityValue.add("PERSON");
    entityValue.add("STATE_OR_PROVINCE");
    entityValue.add("EMAIL");
    System.out.println(entity.extractEntity(text, entityValue));
  }
}
