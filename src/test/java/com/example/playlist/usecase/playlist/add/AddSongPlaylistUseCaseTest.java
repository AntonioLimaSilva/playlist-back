package com.example.playlist.usecase.playlist.add;


import com.example.playlist.domain.Playlist;
import com.example.playlist.domain.Song;
import com.example.playlist.gateway.PlayGateway;
import com.example.playlist.gateway.h2db.PlayGatewayImpl;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import com.example.playlist.gateway.h2db.repository.PlaylistRepository;
import com.example.playlist.usecase.playlist.add.add.AddSongsPlaylistUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class AddSongPlaylistUseCaseTest {

    PlaylistRepository playlistRepository;
    PlayGateway wishGateway;
    AddSongsPlaylistUseCase addUseCase;

    public AddSongPlaylistUseCaseTest() {
        playlistRepository = mock(PlaylistRepository.class);
        wishGateway = new PlayGatewayImpl(playlistRepository);
        addUseCase = new AddSongsPlaylistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve adicionar um playlist de musicas")
    void shouldAddSongsOfPlaylist() {

        var input = playlistMock();
        var playlistEntity = new PlaylistEntity(input);

        when(playlistRepository.save(any())).thenReturn(playlistEntity);

        var out = addUseCase.addSongsInPlaylist(input);

        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getName());
        Assertions.assertNotNull(out.getDescription());
        Assertions.assertEquals(1, out.getSongs().size());
    }


    @DisplayName("Cria mock da wishlist input")
    public Playlist playlistMock() {
        var input = new Playlist();
        input.setName("So as melhores");
        input.setDescription("das antigas");
        input.setSongs(Collections.singletonList(songMock()));
        return input;
    }

    public Song songMock() {
        Song song = new Song();
        song.setTitle("uma arlinda mulher");
        song.setArtist("mamonas assasinas");
        song.setYear("1989");
        song.setGender("Rock");
        return song;
    }

}
