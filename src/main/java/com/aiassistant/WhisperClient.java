package com.aiassistant;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WhisperClient {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public static String transcribe(File audioFile) throws Exception {

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException("OPENAI_API_KEY is not set.");
        }

        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

        HttpClient client = HttpClient.newHttpClient();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(bos, "UTF-8"), true);

        // model
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"model\"\r\n\r\n");
        writer.append("whisper-1").append("\r\n");

        // language -> force English for better accuracy
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"language\"\r\n\r\n");
        writer.append("en").append("\r\n");

        // file
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                .append(audioFile.getName()).append("\"\r\n");
        writer.append("Content-Type: audio/wav\r\n\r\n");
        writer.flush();

        try (FileInputStream inputStream = new FileInputStream(audioFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }

        bos.write("\r\n".getBytes());

        writer.append("--").append(boundary).append("--").append("\r\n");
        writer.close();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/audio/transcriptions"))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(bos.toByteArray()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        // debug: raw Whisper response
        System.out.println("Whisper raw response: " + responseBody);

        if (!responseBody.contains("\"text\":\"")) {
            throw new RuntimeException("Unexpected transcription response: " + responseBody);
        }

        int start = responseBody.indexOf("\"text\":\"") + 8;
        int end = responseBody.indexOf("\"", start);

        return responseBody.substring(start, end);
    }
}