package com.apiexample.webflux.service;

import com.apiexample.webflux.model.Playlist;
import com.apiexample.webflux.repository.PlaylistRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Data
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistRepository repository;


    @Override
    public Flux<Playlist> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Playlist> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        return repository.save(playlist);
    }
}
