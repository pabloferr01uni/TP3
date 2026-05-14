package com.streaming.music.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Artista {
    private UUID id;
    private String nombre;
    private List<Album>albumes=new ArrayList<>(); 
}
