package com.example.demo.clases;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Album {
    private String titulo;
    private String anio;
    private Artista artista;
    private List<Cancion> canciones = new ArrayList<>();
    private Productora productora;
}
