package com.yuliia.vlasenko.imagesearcher.download;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final RestTemplate restTemplate;
    @Value("${imageapi.key}")
    private String apiKey;

    private String token;

    public AuthService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void authenticate() {
        AuthRequest authRequest = new AuthRequest(apiKey);
        AuthResponse authResponse = restTemplate.postForObject("http://interview.agileengine.com/auth", authRequest, AuthResponse.class);
        if (authResponse != null) {
            this.token = authResponse.getToken();
        }
    }

    public String getToken() {
        if (token == null) {
            authenticate();
        }
        return token;
    }
}
