package com.streaming.music.strategy;
import com.streaming.music.clases.Cancion;
import java.util.List;

public class RecomendacionPorPopularidad implements RecomendacionStrategy {
    @Override

    public List<Cancion>recomendar(List<Cancion>catalogo,Cancion base){
        return catalogo.stream()
        .sorted((c1,c2)->Integer.compare(c2.getReproducciones().get(),c1.getReproducciones().get()))
        .limit(5)
        .toList();
    }

}
