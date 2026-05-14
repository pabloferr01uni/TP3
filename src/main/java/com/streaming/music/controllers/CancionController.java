package com.streaming.music.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streaming.music.clases.Cancion;
import com.streaming.music.service.MusicaService;

@RestController
@RequestMapping("/api/canciones")



public class CancionController {


    @Autowired
    private MusicaService musicaService;



    @GetMapping
    public ResponseEntity<List<Cancion>> listarTodas(){
        List<Cancion>lista=musicaService.getListaCanciones();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> buscarPorId(@PathVariable UUID id){

        return musicaService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>>buscarFiltrada(
        @RequestParam(required = false) String titulo,
        @RequestParam(required = false) String artista){
            List<Cancion> resultados = musicaService.filtrarPorTituloYArtista(titulo, artista);
            return ResponseEntity.ok(resultados);
        }
    
    @PostMapping("/{id}/reproducir")
        public ResponseEntity<Void>reproducir(@PathVariable UUID id){
            musicaService.registrarReproduccion(id);
            return ResponseEntity.noContent().build();
        }
}
