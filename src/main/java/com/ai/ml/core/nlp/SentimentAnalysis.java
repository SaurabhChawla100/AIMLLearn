package com.ai.ml.core.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SentimentAnalysis extends AnalyzeSentiments {

  public String getSentimentAnalysis(String statement) {
    try {
      // Initialize the Stanford NLP pipeline
      StanfordCoreNLP pipeline = new StanfordCoreNLP("application.properties");
      // Perform sentiment analysis
      String sentiment = getSentiment(statement, pipeline);
      return sentiment;
    } catch (Throwable ex) {
      log.error("Error in getSentimentAnalysis::"+ ex.getMessage());
      throw ex;
    }
  }

  private static String getSentiment(String text, StanfordCoreNLP pipeline) {
    // Create an Annotation object with the input text
    Annotation annotation = new Annotation(text);

    // Run all the NLP annotators on the text
    pipeline.annotate(annotation);

    // Extract the sentiment from the annotation
    CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
    String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
    return sentiment;
  }
}