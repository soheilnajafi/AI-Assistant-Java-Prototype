package com.aiassistant;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAIClient {

    private static final String API_KEY = "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";

    public static String askAI(String prompt) throws Exception {

        // Escape characters that break JSON
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

        //System.out.println(body);

        int start = body.indexOf("\"content\": \"") + 12;
        int end = body.indexOf("\",", start);

        String message = body.substring(start, end);

        return message.replace("\\n", "\n");
    }
}