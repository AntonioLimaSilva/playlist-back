package com.example.playlist.gateway.h2db.entity.playlist;

import com.example.playlist.domain.Playlist;
import com.example.playlist.gateway.h2db.entity.song.SongEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEntity> songs = new ArrayList<>();

    public PlaylistEntity() {
    }

    public PlaylistEntity(Playlist playlist) {
        this.name = playlist.getName();
        this.description = playlist.getDescription();
        this.songs = playlist.getSongs().stream().map(SongEntity::new).toList();
        this.songs.forEach(s -> s.setPlaylist(this));
    }

    public Playlist toDomain() {

        Playlist playlist = new Playlist();
        playlist.setId(this.id);
        playlist.setName(this.name);
        playlist.setDescription(this.description);
        playlist.setSongs(this.songs.stream().map(SongEntity::toDomain).collect(Collectors.toList()));

        return playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistEntity that = (PlaylistEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
