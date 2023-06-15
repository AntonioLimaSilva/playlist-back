package com.example.playlist.gateway.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {

    @Schema(example = "400")
    private Integer status;
    @Schema(example = "https://playlist.com.br/dados-invalidos")
    private String type;
    @Schema(example = "Dados inválidos")
    private String title;
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;
    @Schema(example = "2022-07-15T11:21:50.902245498Z")
    private OffsetDateTime timestamp;
    @Schema(description = "Lista de objetos ou campos que geraram o erro")
    private List<Object> objects;

    @Builder
    @Getter
    @Schema(name = "ObjectProblem")
    public static class Object {
        private String name;
        private String userMessage;
    }
}

