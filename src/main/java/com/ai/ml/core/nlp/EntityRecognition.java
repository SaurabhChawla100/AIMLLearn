package com.ai.ml.core.nlp;

import java.util.Set;

/**
 * extends this class for using your own implementation of Named Entity Recognition or Extract entity
 */
public abstract class EntityRecognition {
  public abstract String extractEntity(String text, Set<String> entityType);
}
