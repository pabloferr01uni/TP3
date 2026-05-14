package com.streaming.music.strategy;
import com.streaming.music.clases.Cancion;
import java.util.List;

public interface RecomendacionStrategy {
    List<Cancion>recomendar(List<Cancion>catalogo,Cancion base);
    
}