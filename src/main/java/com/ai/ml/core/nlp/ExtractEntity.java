package com.ai.ml.core.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Properties;

public class ExtractEntity extends EntityRecognition {
  public String extractEntity(String text, Set<String> entityType) {
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
    props.setProperty("ner.useSUTime", "0");
    // props.setProperty("sutime.rules", "edu/stanford/nlp/models/sutime/jollyday/Holidays_sutime.xml");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    CoreDocument document = new CoreDocument(text);
    // Annotate the document
    pipeline.annotate(document);
    Map extractEntity = new HashMap();
    for (CoreLabel token : document.tokens()) {
      String entity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
      //System.out.println("value of NamedEntityTagAnnotation::"+ token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
      if (!entity.equalsIgnoreCase("O") && entityType.contains(entity)) {
        System.out.println(token.word() + " - " + entity);
        extractEntity.putIfAbsent(token.word(), entity);
      }
    }
    return extractEntity.toString();
  }
}
