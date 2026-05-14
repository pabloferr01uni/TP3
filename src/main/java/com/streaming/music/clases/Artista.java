package com.streaming.music.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter @Setter
public class Artista {
    private UUID id;
    private String nombre;
    // La inicializamos acá para que nunca sea null y no necesite constructor
    private List<Album> albumes = new ArrayList<>(); 
}