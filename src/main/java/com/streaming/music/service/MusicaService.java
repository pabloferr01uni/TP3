package com.streaming.music.service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.streaming.music.clases.Artista;
import com.streaming.music.clases.Cancion;
import com.streaming.music.clases.Genero;

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

//Promedio de duracion por genero

public Map<Genero,Double>obtenerPormedioPorGenero(List<Cancion>canciones){
    return canciones.stream()
    .collect(Collectors.groupingBy(Cancion::getGenero,
        Collectors.averagingInt(Cancion::getDuracionSegundos)
    ));
}

//Artista mas popular

public Optional<Artista>ArtistaMasPopular(List<Cancion>canciones){
    return canciones.stream()
    .max(Comparator.comparingInt(c->c.getReproducciones().get()))
    .map(Cancion::getArtista);
}

//Distribucion por decadas

public Map<Integer,List<Cancion>>obtenerDistribucionPorDecadas(List<Cancion>canciones){
    return canciones.stream()
    .collect(Collectors.groupingBy(c->{
        int anio=c.getAlbum().getAnio();
        return (anio/10)*10;
    }));
}



}
