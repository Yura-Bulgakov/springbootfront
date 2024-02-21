package com.example.springbootfront.controller;

import com.example.springbootfront.dto.AuthDto;
import com.example.springbootfront.dto.UserDto;
import com.example.springbootfront.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class FrontController {
    private static final String CREATE_PATH = "http://localhost:8080/v1/auth/new";
    private static final String AUTH_PATH = "http://localhost:8080/v1/auth/authenticate";
    private static final String GET_PATH = "http://localhost:8080/v1/info/weather";

    @Autowired
    private RequestService requestService;

    @GetMapping
    public String getWeather() {
        UserDto user = new UserDto(2, "user", "gmail", "1234");
        AuthDto authDto = new AuthDto(user.getName(), user.getPassword());
        requestService.createUser(user, CREATE_PATH);
        String authorizationHeader = requestService.authenticateUser(authDto, AUTH_PATH);
        return requestService.getWeather(authorizationHeader, GET_PATH);
    }
}
