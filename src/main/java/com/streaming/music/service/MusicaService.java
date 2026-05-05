package com.streaming.music.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.streaming.music.clases.Cancion;

@Service


public class MusicaService {
    

public List<Cancion> filtradoPersonalizado(List<Cancion>canciones, String generoBusqueda,String artistaBusqueda,int anioInicio,int anioFin,int ratingMinimo){

    return canciones.stream()
    //Filtrado por genero
    .filter(c ->c.getGenero().name().equalsIgnoreCase(generoBusqueda))
    .collect(Collectors.toList());
    
 }

}
