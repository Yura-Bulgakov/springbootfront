package com.example.springbootfront.service;

import com.example.springbootfront.dto.AuthDto;
import com.example.springbootfront.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestService {
    private final RestTemplate restTemplate;

    @Autowired
    public RequestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String createUser(UserDto user, String url) {
        return restTemplate.postForEntity(url, user, String.class).getBody();
    }

    public String authenticateUser(AuthDto authDto, String url) {
        String token = restTemplate.postForEntity(url, authDto, String.class).getBody();
        return "Bearer " + token;
    }

    public String getWeather(String authorizationHeader, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
