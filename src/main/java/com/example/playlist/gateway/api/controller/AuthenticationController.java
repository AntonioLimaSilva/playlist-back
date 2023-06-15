package com.example.playlist.gateway.api.controller;

import com.example.playlist.gateway.api.controller.dto.JwtAuthenticationResponseDto;
import com.example.playlist.gateway.api.controller.dto.SignUpRequestDto;
import com.example.playlist.gateway.api.controller.dto.SigninRequestDto;
import com.example.playlist.gateway.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDto> signup(@RequestBody @Valid SignUpRequestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signin(@RequestBody @Valid SigninRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}