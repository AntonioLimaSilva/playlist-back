package com.example.playlist.usecase.playlist.add.remove;

import com.example.playlist.exception.NamePlaylistNotFoundException;
import com.example.playlist.gateway.PlayGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RemoveSongsPlaylistUseCase {

    public static final String MSS_NOT_FOUND = "Não existe o nome dessa playlist.";

    private final PlayGateway wishGateway;

    public void removeSongsInPlaylist(String name) {
        var exists = wishGateway.existsBy(name);

        if (!exists) {
            throw new NamePlaylistNotFoundException(MSS_NOT_FOUND);
        }

        wishGateway.remove(name);
    }
}
