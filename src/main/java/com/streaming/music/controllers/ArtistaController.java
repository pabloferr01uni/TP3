package com.streaming.music.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.streaming.music.clases.Artista;
import com.streaming.music.service.MusicaService;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Artista>> listarTodos() {
        return ResponseEntity.ok(musicaService.getListaArtistas());
    }

    @GetMapping("/mas-popular")
    public ResponseEntity<Artista> artistaMasPopular() {
        return musicaService.ArtistaMasPopular(musicaService.getListaCanciones())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable UUID id) {
        return musicaService.buscarArtistaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}