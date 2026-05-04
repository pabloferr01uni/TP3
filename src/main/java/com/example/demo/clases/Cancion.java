package com.example.demo.clases;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.demo.errores.RatingInvalido;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Cancion {
    private UUID id;
    private String titulo;
    private String artista;
    private String album;
    private Genero genero;
    private int duracionSegundos;
    private AtomicInteger reproducciones;
    private double rating;
    private LocalDate fechaLanzamiento;

 

    public Cancion(UUID id, String titulo, String artista, String album, Genero genero, int duracionSegundos, AtomicInteger reproducciones, double rating, LocalDate fechaLanzamiento){

        this.id=id;
        this.titulo=titulo;
        this.artista=artista;
        this.album=album;
        this.genero=genero;
        this.duracionSegundos=duracionSegundos;
        this.reproducciones=reproducciones;
        setRating(rating);
        this.fechaLanzamiento=fechaLanzamiento;

    }

    public void setRating(double rating){
        if(rating>=0.0 && rating<=5.0){
            this.rating=rating;
        }else{
            throw new RatingInvalido(rating);
        }
    }
}
