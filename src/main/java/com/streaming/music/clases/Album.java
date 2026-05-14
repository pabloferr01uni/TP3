package com.streaming.music.clases;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter @Setter
public class Album {
    private UUID id;
    private String titulo;
    private int anio;
    private Artista artista;
}