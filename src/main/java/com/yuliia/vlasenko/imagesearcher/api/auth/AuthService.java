package com.yuliia.vlasenko.imagesearcher.api.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthService {
    private final RestTemplate restTemplate;
    @Value("${imageapi.key}")
    private String apiKey;

    private String token;

    public AuthService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void authenticate() {
        log.info("Authenticate token request...");
        AuthRequest authRequest = new AuthRequest(apiKey);
        AuthResponse authResponse = restTemplate.postForObject("http://interview.agileengine.com/auth", authRequest, AuthResponse.class);
        if (authResponse == null || !authResponse.isAuth()) {
            throw new AuthException("Auth request failed");
        }
        this.token = authResponse.getToken();
    }

    public String getToken() {
        if (token == null) {
            authenticate();
        }
        return token;
    }
}
