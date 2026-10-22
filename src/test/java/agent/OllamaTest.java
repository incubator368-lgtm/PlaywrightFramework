package agent;

public class OllamaTest {

    public static void main(String[] args) throws Exception {

        System.out.println("Connecting to Ollama...");

        OllamaClient client = new OllamaClient();

        String response = client.ask("Say hello. I am testing an AI agent using Java.");

        System.out.println("AI Response:");
        System.out.println(response);
    }
}