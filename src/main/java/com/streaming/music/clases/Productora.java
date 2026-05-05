package com.streaming.music.clases;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Productora {
    private String nombre;
    private List<Album>albumes = new ArrayList<>();
    private List<Artista>artistas = new ArrayList<>();

}
