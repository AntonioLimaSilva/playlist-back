package com.example.playlist.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Playlist {

    private Long id;
    private String name;
    private String description;
    private List<Song> songs = new ArrayList<>();
}
