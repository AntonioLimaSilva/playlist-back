package com.example.playlist.gateway.security;

import com.example.playlist.gateway.api.controller.dto.JwtAuthenticationResponseDto;
import com.example.playlist.gateway.api.controller.dto.SignUpRequestDto;
import com.example.playlist.gateway.api.controller.dto.SigninRequestDto;

public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(SignUpRequestDto request);

    JwtAuthenticationResponseDto signin(SigninRequestDto request);
}
