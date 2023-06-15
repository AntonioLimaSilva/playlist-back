package com.example.playlist.usecase.playlist.add.list;

import com.example.playlist.domain.Playlist;
import com.example.playlist.exception.NamePlaylistNotFoundException;
import com.example.playlist.gateway.PlayGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ListSongsPlaylistUseCase {

    public static final String MSS_NOT_FOUND = "Não existe o nome dessa playlist.";
    private final PlayGateway wishGateway;

    public List<Playlist> listSongsInPlaylist() {
        return wishGateway.findAll();
    }

    public List<Playlist> listNameInPlaylist(String name) {
        var playlists =  wishGateway.findByName(name);

        if (playlists.isEmpty()) {
            throw new NamePlaylistNotFoundException(MSS_NOT_FOUND);
        }

        return playlists;
    }
}
