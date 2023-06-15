package com.example.playlist.gateway.openapi.controller;

import com.example.playlist.gateway.api.controller.dto.JwtAuthenticationResponseDto;
import com.example.playlist.gateway.api.controller.dto.SignUpRequestDto;
import com.example.playlist.gateway.api.controller.dto.SigninRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
public interface AuthenticationControllerOpenApi {

    @Operation(summary = "Cadastra um novo usuario", description = "Cadastro um usuario para usar o app")
    JwtAuthenticationResponseDto signup(SignUpRequestDto request);

    @Operation(summary = "Login para usuario", description = "Login de um usuario para usar o app")
    JwtAuthenticationResponseDto signin(SigninRequestDto request);
}
