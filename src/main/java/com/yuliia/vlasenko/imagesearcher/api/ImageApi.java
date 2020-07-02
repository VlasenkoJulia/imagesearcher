package com.yuliia.vlasenko.imagesearcher.api;

import com.yuliia.vlasenko.imagesearcher.api.auth.AuthService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageApi {
    private final RestTemplate restTemplate;
    private final AuthService authService;

    public ImageApi(RestTemplateBuilder restTemplateBuilder, AuthService authService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authService = authService;
    }

    public Images getImages(int page) {
        return executeRequestWithAuth("http://interview.agileengine.com/images?page={page}", Images.class, page);
    }

    public List<PictureMetaData> getMetaData(List<String> pictureIds) {
        return pictureIds.stream().map(this::getMetaData).collect(Collectors.toList());
    }

    public PictureMetaData getMetaData(String pictureId) {
        return executeRequestWithAuth("http://interview.agileengine.com/images/{id}", PictureMetaData.class, pictureId);
    }

    private <T> T executeRequestWithAuth(String url, Class<T> responseType, Object... params) {
        ResponseEntity<T> responseEntity = executeRequest(url, responseType, params);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.UNAUTHORIZED) {
            authService.authenticate();
            responseEntity = executeRequest(url, responseType, params);
        }
        return responseEntity.getBody();
    }

    private <T> ResponseEntity<T> executeRequest(String url, Class<T> responseType, Object... params) {
        String token = authService.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, responseType, params);
    }
}
