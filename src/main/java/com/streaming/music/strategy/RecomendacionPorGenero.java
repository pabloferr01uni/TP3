package com.streaming.music.strategy;
import java.util.List;
import java.util.Comparator;

import com.streaming.music.clases.Cancion;

public class RecomendacionPorGenero implements RecomendacionStrategy {
    @Override

    public List<Cancion> recomendar(List<Cancion>catalogo,Cancion base){
        return catalogo.stream()
        .filter(c->c.getGenero()==base.getGenero())
        .filter(c->!c.getId().equals(base.getId()))
        .sorted(Comparator.comparingDouble(Cancion::getRating).reversed())
        .toList();
    }
}
