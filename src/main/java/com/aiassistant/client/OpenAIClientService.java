package com.aiassistant.client;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.stereotype.Service;

@Service
public class OpenAIClientService {

    private final OpenAIClient client;

    public OpenAIClientService() {
        this.client = OpenAIOkHttpClient.fromEnv();
    }

    public String getResponse(String userMessage) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(userMessage)
                .model(ChatModel.GPT_4O_MINI)
                .build();

        ChatCompletion completion = client.chat().completions().create(params);

        return completion.choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .findFirst()
                .orElse("No response from AI.");
    }
}