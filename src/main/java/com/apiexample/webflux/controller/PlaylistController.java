package com.apiexample.webflux.controller;

import com.apiexample.webflux.document.Playlist;
import com.apiexample.webflux.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService service;

    @GetMapping("/all")
    public Flux<Playlist> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public Mono<Playlist> save(@RequestBody Playlist playlist) {
        return service.save(playlist);
    }

}
