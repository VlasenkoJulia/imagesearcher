package com.yuliia.vlasenko.imagesearcher.download;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageDownloader {
    private final RestTemplate restTemplate;

    public ImageDownloader(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AuthResponse getAuthToken(String apiKey) {
        AuthRequest authRequest = new AuthRequest(apiKey);
        return restTemplate.postForObject("http://interview.agileengine.com/auth", authRequest, AuthResponse.class);
    }

    public Images getImages(int page, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Images> responseEntity = restTemplate.exchange(
                "http://interview.agileengine.com/images?page={page}", HttpMethod.GET, entity, Images.class, page);
        return responseEntity.getBody();
    }

    public List<PictureMetaData> getMetaData(List<String> pictureIds, String token) {
        return pictureIds.stream().map(id-> getMetaData(id, token)).collect(Collectors.toList());
    }

    public PictureMetaData getMetaData(String pictureId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<PictureMetaData> responseEntity = restTemplate.exchange(
                "http://interview.agileengine.com/images/{id}", HttpMethod.GET, entity, PictureMetaData.class, pictureId);
        return responseEntity.getBody();
    }
}
