package com.example.springbootfront.controller;

import com.example.springbootfront.dto.AuthDto;
import com.example.springbootfront.dto.UserDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class FrontController {
    private static final String CREATE_PATH = "http://localhost:8080/v1/auth/new";
    private static final String AUTH_PATH = "http://localhost:8080/v1/auth/authenticate";
    private static final String GET_PATH = "http://localhost:8080/v1/info/weather";


    @GetMapping
    public String getWeather() {
        UserDto user = new UserDto(2, "user", "gmail", "1234");
        AuthDto authDto = new AuthDto(user.getName(), user.getPassword());
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
        restTemplate.postForEntity(CREATE_PATH, user, String.class);
        ResponseEntity<String> token = restTemplate.postForEntity(AUTH_PATH, authDto, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getBody());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.postForEntity(GET_PATH, entity, String.class).getBody();
    }
}
