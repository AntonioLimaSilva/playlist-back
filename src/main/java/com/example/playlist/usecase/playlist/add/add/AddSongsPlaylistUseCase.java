package com.example.playlist.usecase.playlist.add.add;


import com.example.playlist.domain.Playlist;
import com.example.playlist.gateway.PlayGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AddSongsPlaylistUseCase {

    private final PlayGateway wishGateway;

    public Playlist addSongsInPlaylist(Playlist wishlist) {

        return wishGateway.save(wishlist);
    }
}
