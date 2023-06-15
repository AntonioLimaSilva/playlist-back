package com.example.playlist.gateway.api.controller.dto;

import com.example.playlist.domain.Playlist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputAddSongsPlaylistDto {
    @Schema(example = "Gustavo lima")
    private String name;
    @Schema(example = "Uma linda mulher")
    private String description;
    private Set<SongDto> songs;

    public OutputAddSongsPlaylistDto(Playlist playlist) {
        this.name = playlist.getName();
        this.description = playlist.getDescription();
        this.songs = playlist.getSongs().stream().map(SongDto::new).collect(Collectors.toSet());
    }
}
