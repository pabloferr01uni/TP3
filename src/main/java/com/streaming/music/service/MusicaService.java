package com.streaming.music.service;
import java.util.ArrayList;
import java.util.Collections;
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

//Generar playlist exacta

public List<Cancion>generarPlayListExacta(List<Cancion>canciones,int minutosObjetivo){
    int segundosObjetivo=minutosObjetivo*60;
    return buscarCombinacion(canciones,segundosObjetivo);
}

private List<Cancion>buscarCombinacion(List<Cancion>canciones, int tiempoRestante){
    //Puede pasar que encontremos la suma exacta.
    if (tiempoRestante==0) return new ArrayList<>();
    //Tambien puede ser que no haya mas canciones o el timepo se pasó.
    if(canciones.isEmpty()||tiempoRestante<0)
        return null;

    //Tomamos la primera cancion de la lista actual.
    Cancion actual = canciones.get(0);
    List<Cancion>sublista=canciones.subList(1,canciones.size());

    //Opcion A incluimos la cancion actual.
    List<Cancion>conActual=buscarCombinacion(sublista,tiempoRestante-actual.getDuracionSegundos());
    
    if(conActual!=null){
        List<Cancion> resultado = new ArrayList<>(conActual);
        resultado.add(actual);
        return resultado;
    }
//Opcion B no incluimos la cancion actual y probamos con el resultado
return buscarCombinacion(sublista, tiempoRestante);
    
}

//Busqueda binaria por titulo

public int buscarCancionPorTitulo(List<Cancion>canciones, String titulo){
    //Primero ordenamos por el titulo (Orden natural)
    canciones.sort(Comparator.comparing(Cancion::getTitulo));

    //Creamos un objeto temporal para poder comparar.
    Cancion buscada = new Cancion();
    buscada.setTitulo(titulo);

    return Collections.binarySearch(canciones,buscada,Comparator.comparing(Cancion::getTitulo));
}

//Ordenamiento filtradoPersonalizado

public void ordenarCatalogoPersonalizado(List<Cancion>canciones){
    canciones.sort(
        Comparator.comparing((Cancion c)-> c.getArtista().getNombre())
        .thenComparing(Cancion::getFechaLanzamiento)
        .reversed()
    );
}

//Busqueda lineal con predicados multiples(genero,año y rating)

public List<Cancion> filtrarPorPredicadosMultiples(List<Cancion>canciones,String genero, int anioReferencia, double ratingMinimo){
    return canciones.stream()
    .filter(c->c.getGenero().name().equalsIgnoreCase(genero))
    .filter(c->c.getFechaLanzamiento().getYear()>anioReferencia)
    .filter(c->c.getRating()>ratingMinimo)
    .toList();
}



}
