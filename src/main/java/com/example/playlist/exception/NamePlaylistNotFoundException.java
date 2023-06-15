package com.example.playlist.exception;

public class NamePlaylistNotFoundException extends RuntimeException {
    public NamePlaylistNotFoundException(String message) {
        super(message);
    }
}
