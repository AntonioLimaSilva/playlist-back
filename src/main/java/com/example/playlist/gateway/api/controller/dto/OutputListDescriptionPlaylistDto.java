package com.example.playlist.gateway.api.controller.dto;

import com.example.playlist.domain.Playlist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputListDescriptionPlaylistDto {

    @Schema(example = "Gustavo lima")
    private String name;
    @Schema(example = "Uma linda mulher")
    private String description;
    @Size(max = 20, message = "Limite máximo de 20 produtos.")
    private Set<SongDto> songs;

    public OutputListDescriptionPlaylistDto(Playlist playlist) {
        this.name = playlist.getName();
        this.description = playlist.getDescription();
        this.songs = playlist.getSongs().stream().map(SongDto::new).collect(Collectors.toSet());
    }
}
