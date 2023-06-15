package com.example.playlist.gateway.api.controller.dto;

import com.example.playlist.domain.Playlist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class InputAddSongsPlaylistDto {

    @Schema(example = "So as melhores")
    @NotBlank
    private String name;
    @Schema(example = "musicas para festas")
    @NotBlank
    private String description;

    @Size(max = 20, message = "Limite máximo de 20 songs.")
    private Set<SongDto> songs = new HashSet<>();

    public Playlist toDomain() {

        Playlist wishlist = new Playlist();
        wishlist.setName(this.name);
        wishlist.setDescription(this.description);
        wishlist.setSongs(songs.stream().map(SongDto::toDomain).toList());

        return wishlist;
    }

}


