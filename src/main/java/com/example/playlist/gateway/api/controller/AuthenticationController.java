package com.example.playlist.gateway.api.controller;

import com.example.playlist.gateway.api.controller.dto.JwtAuthenticationResponseDto;
import com.example.playlist.gateway.api.controller.dto.SignUpRequestDto;
import com.example.playlist.gateway.api.controller.dto.SigninRequestDto;
import com.example.playlist.gateway.openapi.controller.AuthenticationControllerOpenApi;
import com.example.playlist.gateway.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerOpenApi {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    @Override
    public JwtAuthenticationResponseDto signup(@RequestBody @Valid SignUpRequestDto request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    @Override
    public JwtAuthenticationResponseDto signin(@RequestBody @Valid SigninRequestDto request) {
        return authenticationService.signin(request);
    }
}