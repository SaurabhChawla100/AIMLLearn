package com.ai.ml.core.nlp.driver;

import edu.stanford.nlp.pipeline.*;

import java.util.*;

public class CoreferenceResolutionExample {
  public static void main(String[] args) {
    // Set up the pipeline properties for coreference resolution
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref");

    // Create the StanfordCoreNLP object with the given properties
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Example text with coreferences
    String text = "John went to the store. He bought some groceries. Mona went to park. CoreferenceResolutionExample is great";

    // Create a CoreDocument object from the input text
    CoreDocument document = new CoreDocument(text);

    // Annotate the document
    pipeline.annotate(document);

    // Retrieve the coreference chain information
    System.out.println("Coreference Chains:");

    Map chains = document.corefChains();
    // Iterate through the coreference chains

    for (Object chain : chains.keySet()) {
      System.out.println("Coreference chain 11:"+ chain);
      System.out.println(chains.get(chain).toString());
    }
  }
}
