package com.example.playlist.gateway.api.controller.dto;

import com.example.playlist.domain.Song;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {

    @Schema(example = "jumento celestino")
    private String title;
    @Schema(example = "Mamonas assasina")
    private String artist;
    @Schema(example = "1988")
    private String year;
    @Schema(example = "pop rock")
    private String gender;

    public SongDto() {}

    public SongDto(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.year = song.getYear();
        this.gender = song.getGender();
    }

    public Song toDomain() {
        var song = new Song();
        song.setTitle(this.title);
        song.setArtist(this.artist);
        song.setYear(this.year);
        song.setGender(this.gender);
        return song;
    }
}
