package com.example.CRUD.external;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalApiService {

    private final WebClient webClient = WebClient.create("https://api.example.com");

    public String sendData(Object data) {
        return webClient.post()
                .uri("/notify")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}