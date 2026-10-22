package agent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OllamaClient {

    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public OllamaClient() {
        httpClient = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public String ask(String prompt) throws Exception {

        System.out.println(">>> OllamaClient.ask() called");
        System.out.println(">>> Prompt sent to Ollama:");
        System.out.println(prompt);

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode requestJson = mapper.createObjectNode();

        // Ollama model
        requestJson.put("model", "qwen3:8b");

        // Disable thinking for this simple decision
        requestJson.put("think", false);

        // Model options
        ObjectNode options = mapper.createObjectNode();
        options.put("temperature", 0);
        options.put("num_predict", 20);

        requestJson.set("options", options);

        // Messages
        ArrayNode messages = requestJson.putArray("messages");

        ObjectNode message = messages.addObject();

        message.put("role", "user");
        message.put("content", prompt);

        // Do not stream the response
        requestJson.put("stream", false);

        System.out.println(">>> JSON request created successfully");

        String json = mapper.writeValueAsString(requestJson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/chat"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        System.out.println(">>> Raw Ollama response:");
        System.out.println(response.body());

        JsonNode root = mapper.readTree(response.body());

        String content = root
                .path("message")
                .path("content")
                .asText();

        System.out.println(">>> Extracted content:");
        System.out.println(content);

        return content.trim();
    }
}