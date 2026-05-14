package com.streaming.music.strategy;

import java.time.LocalDate;
import java.util.List;

import com.streaming.music.clases.Cancion;

public class RecomendacionDescubrimiento implements RecomendacionStrategy {
    @Override

    public List<Cancion>recomendar(List<Cancion>catalogo,Cancion base){
        return catalogo.stream()
        .filter(c->c.getReproducciones().get()<1000)
        .filter(c->c.getFechaLanzamiento().isAfter(LocalDate.now().minusYears(2)))
        .filter(c->c.getGenero()!=base.getGenero())
        .toList();
    }
    
}
