package ai;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
 
import java.util.Scanner;

@QuarkusMain
public class OllamaCliApp implements QuarkusApplication {

    @Inject
    OllamaAi ollamaAi;

    @Override
    public int run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔮 Ask Ollama AI anything (type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String question = scanner.nextLine();

            if ("exit".equalsIgnoreCase(question)) {
                System.out.println("👋 Exiting...");
                break;
            }

            String response = ollamaAi.answerQuestion(question);
            System.out.println("🤖 Ollama AI: " + response);
        }

        scanner.close();
        return 0;
    }
}
