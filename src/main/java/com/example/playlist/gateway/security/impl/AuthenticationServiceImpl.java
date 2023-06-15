package com.example.playlist.gateway.security.impl;

import com.example.playlist.exception.EmailInvalidException;
import com.example.playlist.gateway.api.controller.dto.JwtAuthenticationResponseDto;
import com.example.playlist.gateway.api.controller.dto.SignUpRequestDto;
import com.example.playlist.gateway.api.controller.dto.SigninRequestDto;
import com.example.playlist.gateway.h2db.entity.user.Role;
import com.example.playlist.gateway.h2db.entity.user.UserEntity;
import com.example.playlist.gateway.h2db.repository.UserRepository;
import com.example.playlist.gateway.security.AuthenticationService;
import com.example.playlist.gateway.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponseDto signup(SignUpRequestDto request) {
        var user = UserEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponseDto signin(SigninRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailInvalidException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }
}
