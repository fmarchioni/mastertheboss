
//JAVA 21
//DEPS dev.langchain4j:langchain4j:0.31.0
//DEPS dev.langchain4j:langchain4j-ollama:0.31.0
import java.time.Duration;

 
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class BasicDemo {
  private static final String MODEL = "llama3";
  private static final String BASE_URL = "http://localhost:11434";
  private static Duration timeout = Duration.ofSeconds(120);
    public static void main(String[] args) {
     
    ChatLanguageModel model = OllamaChatModel.builder()
        .baseUrl(BASE_URL)
        .modelName(MODEL)
        .timeout(timeout)
        .build();
    System.out.println("Welcome to Llama 3! Ask me a question !");
    String question = System.console().readLine();

    String answer = model.generate(question);
    System.out.println(answer); 
    
    }
}
