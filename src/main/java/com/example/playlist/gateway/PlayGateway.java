package com.example.playlist.gateway;

import com.example.playlist.domain.Playlist;

import java.util.List;

public interface PlayGateway {


    Playlist save(Playlist playlist);
    List<Playlist> findAll();
    List<Playlist> findByName(String name);
    void remove(String name);
    boolean existsBy(String name);
}
