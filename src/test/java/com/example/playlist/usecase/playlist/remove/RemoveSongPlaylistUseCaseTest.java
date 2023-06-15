package com.example.playlist.usecase.playlist.remove;


import com.example.playlist.domain.Playlist;
import com.example.playlist.domain.Song;
import com.example.playlist.exception.NamePlaylistNotFoundException;
import com.example.playlist.gateway.PlayGateway;
import com.example.playlist.gateway.h2db.PlayGatewayImpl;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import com.example.playlist.gateway.h2db.repository.PlaylistRepository;
import com.example.playlist.usecase.playlist.add.list.ListSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.remove.RemoveSongsPlaylistUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class RemoveSongPlaylistUseCaseTest {

    PlaylistRepository playlistRepository;
    PlayGateway wishGateway;
    RemoveSongsPlaylistUseCase removeUseCase;

    public RemoveSongPlaylistUseCaseTest() {
        playlistRepository = mock(PlaylistRepository.class);
        wishGateway = new PlayGatewayImpl(playlistRepository);
        removeUseCase = new RemoveSongsPlaylistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve remover por nome da playlist")
    void shouldFindByNameSongsOfPlaylist() {

        var nameList = "So as melhores";

        when(playlistRepository.existsByName(nameList)).thenReturn(true);

        removeUseCase.removeSongsInPlaylist(nameList);

        verify(playlistRepository, atLeastOnce()).existsByName(nameList);
        verify(playlistRepository, atLeastOnce()).deleteByName(nameList);

    }

    @Test
    @DisplayName("Deve remover por nome da playlist")
    void shouldFailByNameSongsOfPlaylist() {

        var nameList = "So as melhores";

        when(playlistRepository.existsByName(nameList)).thenReturn(false);

        assertThrows(NamePlaylistNotFoundException.class, () -> removeUseCase.removeSongsInPlaylist(nameList));

        verify(playlistRepository, atLeastOnce()).existsByName(nameList);

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
