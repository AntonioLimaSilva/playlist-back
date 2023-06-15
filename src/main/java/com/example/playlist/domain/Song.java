package com.example.playlist.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Song {

    private String title;
    private String artist;
    private String year;
    private String gender;
}
