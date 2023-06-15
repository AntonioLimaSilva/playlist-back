package com.example.playlist.gateway.h2db;


import com.example.playlist.domain.Playlist;
import com.example.playlist.gateway.PlayGateway;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import com.example.playlist.gateway.h2db.repository.PlaylistRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PlayGatewayImpl implements PlayGateway {

    private final PlaylistRepository playlistRepository;

    public PlayGatewayImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    @Override
    public Playlist save(Playlist wishlist) {
        var entity = playlistRepository.save(new PlaylistEntity(wishlist));
        return entity.toDomain();
    }

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll().stream().map(PlaylistEntity::toDomain).toList();
    }

    @Override
    public List<Playlist> findByName(String name) {
        return playlistRepository.findDistinctByNameIgnoreCase(name).stream().map(PlaylistEntity::toDomain).toList();
    }

    @Transactional
    @Override
    public void remove(String name) {
        playlistRepository.deleteByName(name);
    }

    @Override
    public boolean existsBy(String name) {
        return playlistRepository.existsByName(name);
    }

}
