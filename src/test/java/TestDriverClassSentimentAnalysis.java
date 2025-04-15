import com.ai.ml.core.nlp.SentimentAnalysis;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDriverClassSentimentAnalysis {
  public static void main(String[] args) {
    String text = "Simplest codings is the best place to learn and grow. I am glad to be here.";
    SentimentAnalysis sentiments = new SentimentAnalysis();
    String sentiment = sentiments.getSentimentAnalysis(text);
    // Display the result
    log.info("Text: {}, Sentiment: {}",text, sentiment);
    // Another Sample text for sentiment analysis
    text = "I hate this place. I am not coming back here again. I am very disappointed.";

    // Perform sentiment analysis
    sentiment = sentiments.getSentimentAnalysis(text);

    // Display the result
    log.info("Text: {}, Sentiment: {}",text, sentiment);

  }

}
