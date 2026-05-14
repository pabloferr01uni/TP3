package com.streaming.music.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.streaming.music.clases.Album;
import com.streaming.music.service.MusicaService;

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Album>> listarTodos() {
        return ResponseEntity.ok(musicaService.getListaAlbumes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable UUID id) {
        return musicaService.buscarAlbumPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Album>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(musicaService.buscarAlbumesPorNombre(nombre));
    }
}