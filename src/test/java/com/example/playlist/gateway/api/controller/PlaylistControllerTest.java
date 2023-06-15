package com.example.playlist.gateway.api.controller;

import com.example.playlist.commons.util.JsonUtil;
import com.example.playlist.domain.Playlist;
import com.example.playlist.domain.Song;
import com.example.playlist.gateway.PlayGateway;
import com.example.playlist.gateway.exceptionhandler.ApiExceptionHandler;
import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import com.example.playlist.gateway.h2db.repository.PlaylistRepository;
import com.example.playlist.usecase.playlist.add.add.AddSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.list.ListSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.remove.RemoveSongsPlaylistUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class PlaylistControllerTest {


    private static final String URL = "http://localhost:8080/playlist";
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    ObjectMapper mapper;
    MockMvc mockMvc;
    PlaylistRepository playlistRepository;
    PlayGateway playGateway;
    PlaylistController playlistController;
    AddSongsPlaylistUseCase addUseCase;
    ListSongsPlaylistUseCase listUseCase;
    RemoveSongsPlaylistUseCase removeUseCase;

    public PlaylistControllerTest() {
        mapper = new ObjectMapper();
        playlistRepository = mock(PlaylistRepository.class);
        playGateway = mock(PlayGateway.class);
        addUseCase = new AddSongsPlaylistUseCase(playGateway);
        listUseCase = new ListSongsPlaylistUseCase(playGateway);
        removeUseCase = new RemoveSongsPlaylistUseCase(playGateway);
        playlistController = new PlaylistController(addUseCase, listUseCase, removeUseCase);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(playlistController).setControllerAdvice(new ApiExceptionHandler(messageSource))
                .build();
    }

    @Test
    @DisplayName("Deve criar uma playlist com musicas")
    public void shouldReturnStatusCreatedWhenAddPlaylist() throws Exception {

        var playlist = playlistMock();

        when(playGateway.save(any())).thenReturn(playlist);

        String requestJson = JsonUtil.createRequestJson(playlist);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.post(URL+"/lists").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(responseAsString.length() > 0);
    }

    @Test
    @DisplayName("Deve criar uma playlist com musicas")
    public void shouldFindAllStatusOkWhenSearch() throws Exception {

        var playlist = playlistMock();

        when(playGateway.findAll()).thenReturn(Collections.singletonList(playlist));

        String requestJson = JsonUtil.createRequestJson(playlist);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.get(URL+"/lists").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(responseAsString.length() > 0);
    }

    @Test
    @DisplayName("Deve criar uma playlist dado um nome")
    public void shouldFindByNameStatusOkWhenSearch() throws Exception {

        var playlist = playlistMock();
        var listName = "So as melhores";

        when(playGateway.findByName(listName)).thenReturn(Collections.singletonList(playlist));

        String requestJson = JsonUtil.createRequestJson(playlist);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.get(URL+"/lists/{listName}", listName).contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(responseAsString.length() > 0);
    }

    @Test
    @DisplayName("Deve retonar status not found se nao existe por nome")
    public void shouldFindByNameStatusNotFoundWhenSearch() throws Exception {

        var playlist = playlistMock();
        var listName = "So as melhores1";

        String requestJson = JsonUtil.createRequestJson(playlist);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.get(URL+"/lists/{listName}", listName).contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(responseAsString.length() > 0);
    }

    @Test
    @DisplayName("Deve remover uma playlist dado um nome")
    public void shouldRemoveByNameStatusOkWhenDelete() throws Exception {

        var listName = "So as melhores";

        when(playGateway.existsBy(listName)).thenReturn(true);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/list/{listName}", listName).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent()).andReturn().getResponse().getContentAsString();

        int notContentSize = 0;
        Assertions.assertEquals(notContentSize, responseAsString.length());
    }

    @Test
    @DisplayName("Deve retornar not fount quando tentar remover uma playlist dado um nome")
    public void shouldRemoveByNameStatusNotFoundWhenDelete() throws Exception {

        var listName = "So as melhores";

        when(playGateway.existsBy(listName)).thenReturn(false);

        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/list/{listName}", listName).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(responseAsString.length() > 0);
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
