package com.featurevote.bff.service;

import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {

    private final OpenAIClient openAIClient;

    @Autowired
    public LLMService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String suggestBoard(String description, List<String> boards) {
        String boardList = String.join("\n", boards);

        String prompt = """
                Based on the feature description below, suggest the best board:
                Boards:
                %s
                
                Feature:
                "%s"
                
                Reply only with the board name.
                """.formatted(boardList, description);

        ChatCompletionCreateParams  params = ChatCompletionCreateParams.builder()
                .model("gpt-4o")
                .addSystemMessage("You are a product assistant.")
                .addUserMessage(prompt)
                .temperature(0.4)
                .build();

        ChatCompletion response = openAIClient.chat().completions().create(params);
        System.out.println(response.choices().get(0).message().content().get().trim());
        return response.choices().get(0).message().content().get().trim();
    }

}
