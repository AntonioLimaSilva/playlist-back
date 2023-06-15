package com.example.playlist.usecase.playlist.list;


import com.example.playlist.domain.Playlist;
import com.example.playlist.domain.Song;
import com.example.playlist.gateway.PlayGateway;
import com.example.playlist.gateway.h2db.PlayGatewayImpl;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import com.example.playlist.gateway.h2db.repository.PlaylistRepository;
import com.example.playlist.usecase.playlist.add.add.AddSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.list.ListSongsPlaylistUseCase;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class ListSongPlaylistUseCaseTest {

    PlaylistRepository playlistRepository;
    PlayGateway wishGateway;
    ListSongsPlaylistUseCase listUseCase;

    public ListSongPlaylistUseCaseTest() {
        playlistRepository = mock(PlaylistRepository.class);
        wishGateway = new PlayGatewayImpl(playlistRepository);
        listUseCase = new ListSongsPlaylistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve listar as musicas")
    void shouldFindAllSongsOfPlaylist() {

        var input = playlistMock();
        var playlistEntity = new PlaylistEntity(input);

        when(playlistRepository.findAll()).thenReturn(List.of(playlistEntity));

        var list = listUseCase.listSongsInPlaylist();

        list.forEach(out -> {
            Assertions.assertNotNull(out);
            Assertions.assertNotNull(out.getName());
            Assertions.assertNotNull(out.getDescription());
            Assertions.assertEquals(1, out.getSongs().size());
        });

    }

    @Test
    @DisplayName("Deve listar por nome da playlist")
    void shouldFindByNameSongsOfPlaylist() {

        var input = playlistMock();
        var playlistEntity = new PlaylistEntity(input);

        var nameList = "So as melhores";

        when(playlistRepository.findDistinctByNameIgnoreCase(nameList)).thenReturn(List.of(playlistEntity));

        var list = listUseCase.listNameInPlaylist(nameList);

        list.forEach(out -> {
            Assertions.assertNotNull(out);
            Assertions.assertNotNull(out.getName());
            Assertions.assertNotNull(out.getDescription());
            Assertions.assertEquals(1, out.getSongs().size());
        });

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
