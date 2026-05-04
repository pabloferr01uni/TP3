package com.example.demo.clases;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Artista {
    private String nombre;
    private List<Album>albumes=new ArrayList<>(); 
}
