package com.streaming.music.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Album {
    private UUID id;
    private String titulo;
    private int anio;
    private Artista artista;
    private List<Cancion> canciones = new ArrayList<>();
    private Productora productora;
}
