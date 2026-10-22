package agent;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class OpenAITest {

    public static void main(String[] args) {

        System.out.println("Connecting to OpenAI...");

        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Say hello. I am testing an AI agent using Java and Playwright.")
                .model(ChatModel.GPT_5_2)
                .build();

        Response response = client.responses().create(params);

        response.output()
                .stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .forEach(outputText ->
                        System.out.println("AI Response: " + outputText.text()));
    }
}