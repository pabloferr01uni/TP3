package com.streaming.music.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.streaming.music.clases.Cancion;

@Service


public class MusicaService {
    
//Filtro general
public List<Cancion> filtradoPersonalizado(List<Cancion>canciones, String generoBusqueda,String artistaBusqueda,int anioInicio,int anioFin,int ratingMinimo){

    return canciones.stream()
    //Filtrado por genero
    .filter(c ->c.getGenero().name().equalsIgnoreCase(generoBusqueda))
    //Filtrado por artista
    .filter(c -> c.getAlbum().getArtista().getNombre().equalsIgnoreCase(artistaBusqueda))
    //Filtrado por año
    .filter(c->c.getAlbum().getAnio()>= anioInicio && c.getAlbum().getAnio()<= anioFin)
    //Filtrado por rating minimo
    .filter(c->c.getRating()>= ratingMinimo)
    .collect(Collectors.toList());

    
 }

 //Top 10 con mas reproducciones
 public List<Cancion> obtenerTop10MasReproducidos(List<Cancion>canciones){
    return canciones.stream()
    .sorted((c1,c2)-> Integer.compare(c2.getReproducciones().get(),c1.getReproducciones().get()))
    .limit(10)
    .collect(Collectors.toList());

}

}
