package com.aiassistant;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAIClient {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public static String askAI(String prompt) throws Exception {

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException("OPENAI_API_KEY is not set.");
        }

        prompt = prompt
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");

        String json = """
        {
          "model": "gpt-4o-mini",
          "messages": [
            {"role": "user", "content": "%s"}
          ]
        }
        """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        if (!body.contains("\"content\": \"")) {
            throw new RuntimeException("Unexpected API response: " + body);
        }

        int start = body.indexOf("\"content\": \"") + 12;
        int end = body.indexOf("\",", start);

        String message = body.substring(start, end);
        return message.replace("\\n", "\n");
    }
}