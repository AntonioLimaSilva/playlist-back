package com.example.playlist.gateway.openapi.controller;

import com.example.playlist.gateway.api.controller.dto.InputAddSongsPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputAddSongsPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputListDescriptionPlaylistDto;
import com.example.playlist.gateway.api.controller.dto.OutputListSongsPlaylistDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Playlist")
public interface PlaylistControllerOpenApi {

    @Operation(summary = "Cadastra uma playlist", description = "Cadastro de uma playlist com musicas")
    OutputAddSongsPlaylistDto addSongsInPlaylist(InputAddSongsPlaylistDto input);

    @Operation(summary = "Lista as playlist")
    List<OutputListSongsPlaylistDto> listSongsInPlaylist();

    @Operation(summary = "Lista as musicas por nome da playlist")
    List<OutputListDescriptionPlaylistDto> listDescriptionSongsInPlaylist(String listName);

    @Operation(summary = "Excluir uma cidade por ID",responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID da playlist inválida",
                    content = @Content(schema = @Schema(ref = "Problem"))
            ),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada",
                    content = @Content(schema = @Schema(ref = "Problem"))
            )
    })
    void removePlaylist(String listName);
}
