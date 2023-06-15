package com.example.playlist.gateway.api.controller;

import com.example.playlist.domain.Playlist;
import com.example.playlist.gateway.api.controller.dto.InputAddSongsPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputAddSongsPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputListDescriptionPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputListSongsPlaylistDto;
import com.example.playlist.gateway.openapi.controller.PlaylistControllerOpenApi;
import com.example.playlist.usecase.playlist.add.add.AddSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.list.ListSongsPlaylistUseCase;
import com.example.playlist.usecase.playlist.add.remove.RemoveSongsPlaylistUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/playlist")
public class PlaylistController implements PlaylistControllerOpenApi {

    private final AddSongsPlaylistUseCase addUseCase;
    private final ListSongsPlaylistUseCase listUseCase;
    private final RemoveSongsPlaylistUseCase removeUseCase;

    @PostMapping("/lists")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public OutputAddSongsPlaylistDto addSongsInPlaylist(@RequestBody @Valid InputAddSongsPlaylistDto input) {

        Playlist playlist = addUseCase.addSongsInPlaylist(input.toDomain());

        return new OutputAddSongsPlaylistDto(playlist);
    }

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<OutputListSongsPlaylistDto> listSongsInPlaylist() {

        var playlists = listUseCase.listSongsInPlaylist();

        return playlists.stream().map(OutputListSongsPlaylistDto::new).toList();
    }

    @GetMapping("/lists/{listName}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<OutputListDescriptionPlaylistDto> listDescriptionSongsInPlaylist(@PathVariable String listName) {

        var listPlay = listUseCase.listNameInPlaylist(listName);

        return listPlay.stream().map(OutputListDescriptionPlaylistDto::new).toList();
    }


    @DeleteMapping("/list/{listName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void removePlaylist(@PathVariable String listName) {
        removeUseCase.removeSongsInPlaylist(listName);
    }
}
