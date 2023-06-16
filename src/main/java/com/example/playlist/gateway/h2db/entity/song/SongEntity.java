package com.example.playlist.gateway.h2db.entity.song;

import com.example.playlist.domain.Song;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "song")
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String yearSong;
    private String gender;
    @ManyToOne
    @JoinColumn(nullable = false)
    private PlaylistEntity playlist;

    public SongEntity() {}

    public SongEntity(Song song) {
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.yearSong = song.getYear();
        this.gender = song.getGender();
    }

    public Song toDomain() {

        var song = new Song();
        song.setTitle(this.title);
        song.setArtist(this.artist);
        song.setYear(this.yearSong);
        song.setGender(this.gender);

        return song;
    }
}
