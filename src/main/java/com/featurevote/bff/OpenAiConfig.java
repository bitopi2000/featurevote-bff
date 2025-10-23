package com.featurevote.bff;

import com.openai.client.OpenAIClient;
import com.openai.client.OpenAIClientImpl;
import com.openai.client.okhttp.OkHttpClient;
import com.openai.core.ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Bean
    public OpenAIClient openAI() {
        return new OpenAIClientImpl(ClientOptions.builder()
                .apiKey(this.openAiApiKey)
                .httpClient(OkHttpClient.builder().build())
                .build());
    }
}
