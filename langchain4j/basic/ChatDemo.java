//JAVA 21
//DEPS dev.langchain4j:langchain4j:0.31.0
//DEPS dev.langchain4j:langchain4j-ollama:0.31.0

 
import java.time.Duration;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class ChatDemo {
  private static final String MODEL = "llama3";
  private static final String BASE_URL = "http://localhost:11434";
  private static Duration timeout = Duration.ofSeconds(120);

  public static void main(String[] args) {

    ChatLanguageModel model = OllamaChatModel.builder()
        .baseUrl(BASE_URL)
        .modelName(MODEL)
        .timeout(timeout)
        .build();
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    ConversationalChain chain = ConversationalChain.builder()
        .chatLanguageModel(model)
        .chatMemory(chatMemory)
        .build();
    String answer = chain.execute("Hello my name is Francesco!");
    System.out.println(answer);

    answer = chain.execute("Do you remember my name?");
    System.out.println(answer);
  }
}
