package com.streaming.music.config;

import com.streaming.music.clases.*;
import com.streaming.music.service.MusicaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(MusicaService service) {
        return args -> {
            
            // --- ARTISTA 1: DUKI ---
            Artista duki = new Artista();
            duki.setId(UUID.randomUUID());
            duki.setNombre("Duki");

            Album ameri = new Album();
            ameri.setId(UUID.randomUUID());
            ameri.setTitulo("Ameri");
            ameri.setAnio(2023);
            ameri.setArtista(duki);

            // Cancion 1
            Cancion c1 = new Cancion();
            c1.setId(UUID.randomUUID());
            c1.setTitulo("Rockstar");
            c1.setDuracionSegundos(180);
            c1.setRating(4.9);
            c1.setFechaLanzamiento(LocalDate.of(2023, 6, 22));
            c1.setGenero(Genero.TRAP);
            c1.setAlbum(ameri);
            c1.setArtista(duki); // Corregido: ya no saldrá null
            c1.getReproducciones().set(50000);
            service.getListaCanciones().add(c1);

            // Cancion 2
            Cancion c2 = new Cancion();
            c2.setId(UUID.randomUUID());
            c2.setTitulo("Harakiri");
            c2.setDuracionSegundos(210);
            c2.setRating(4.5);
            c2.setFechaLanzamiento(LocalDate.of(2024, 1, 15));
            c2.setGenero(Genero.TRAP);
            c2.setAlbum(ameri);
            c2.setArtista(duki); // Corregido
            c2.getReproducciones().set(800);
            service.getListaCanciones().add(c2);

            // --- ARTISTA 2: BIZARRAP ---
            Artista biza = new Artista();
            biza.setId(UUID.randomUUID());
            biza.setNombre("Bizarrap");

            Album sessions = new Album();
            sessions.setId(UUID.randomUUID());
            sessions.setTitulo("BZRP Sessions");
            sessions.setAnio(2022);
            sessions.setArtista(biza);

            // Cancion 3
            Cancion c3 = new Cancion();
            c3.setId(UUID.randomUUID());
            c3.setTitulo("Session 52");
            c3.setDuracionSegundos(198);
            c3.setRating(5.0);
            c3.setFechaLanzamiento(LocalDate.of(2022, 7, 6));
            c3.setGenero(Genero.ELECTRONICA);
            c3.setAlbum(sessions);
            c3.setArtista(biza); // Corregido
            c3.getReproducciones().set(500000);
            service.getListaCanciones().add(c3);

            // Cancion 4
            Cancion c4 = new Cancion();
            c4.setId(UUID.randomUUID());
            c4.setTitulo("Nueva Ola");
            c4.setDuracionSegundos(185);
            c4.setRating(3.9);
            c4.setFechaLanzamiento(LocalDate.of(2024, 3, 20));
            c4.setGenero(Genero.ELECTRONICA);
            c4.setAlbum(sessions);
            c4.setArtista(biza); // Corregido
            c4.getReproducciones().set(300);
            service.getListaCanciones().add(c4);

            // --- ARTISTA 3: ARCTIC MONKEYS ---
            Artista arctic = new Artista();
            arctic.setId(UUID.randomUUID());
            arctic.setNombre("Arctic Monkeys");

            Album am = new Album();
            am.setId(UUID.randomUUID());
            am.setTitulo("AM");
            am.setAnio(2013);
            am.setArtista(arctic);

            // Cancion 5
            Cancion c5 = new Cancion();
            c5.setId(UUID.randomUUID());
            c5.setTitulo("Do I Wanna Know?");
            c5.setDuracionSegundos(272);
            c5.setRating(4.9);
            c5.setFechaLanzamiento(LocalDate.of(2013, 6, 18));
            c5.setGenero(Genero.ROCK);
            c5.setAlbum(am);
            c5.setArtista(arctic); // Corregido
            c5.getReproducciones().set(900000);
            service.getListaCanciones().add(c5);

            System.out.println(">> Catálogo de 5 canciones cargado y corregido sin nulls.");
        };
    }
}