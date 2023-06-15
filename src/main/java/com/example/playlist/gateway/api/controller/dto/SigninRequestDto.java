package com.example.playlist.gateway.api.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequestDto {

    @Schema(example = "joao@gmail.com")
    @NotBlank
    private String email;
    @Schema(example = "1234")
    @NotBlank
    private String password;
}
