package com.streaming.music.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.streaming.music.clases.Cancion;
import com.streaming.music.service.MusicaService;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Cancion>> listarTodas() {
        return ResponseEntity.ok(musicaService.getListaCanciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> buscarPorId(@PathVariable UUID id) {
        return musicaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscarFiltrada(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String artista) {
        List<Cancion> resultados = musicaService.filtrarPorTituloYArtista(titulo, artista);
        return ResponseEntity.ok(resultados);
    }


    @GetMapping("/top10")
    public ResponseEntity<List<Cancion>> obtenerTop10() {
        return ResponseEntity.ok(musicaService.obtenerTop10MasReproducidos(musicaService.getListaCanciones()));
    }

    @GetMapping("/playlist-exacta")
    public ResponseEntity<List<Cancion>> playlistExacta(@RequestParam int minutos) {
        List<Cancion> playlist = musicaService.generarPlayListExacta(musicaService.getListaCanciones(), minutos);
        return (playlist != null) ? ResponseEntity.ok(playlist) : ResponseEntity.noContent().build();
    }

    @GetMapping("/recomendaciones")
    public ResponseEntity<List<Cancion>> obtenerRecomendaciones(
            @RequestParam String estrategia,
            @RequestParam(required = false) String valor) {
        // Llamamos al nuevo método que agregaremos al Service
        return ResponseEntity.ok(musicaService.recomendar(estrategia, valor));
    }

    @PostMapping("/{id}/reproducir")
    public ResponseEntity<Void> reproducir(@PathVariable UUID id) {
        musicaService.registrarReproduccion(id);
        return ResponseEntity.noContent().build();
    }
}