package com.apiexample.webflux;


import com.apiexample.webflux.model.Playlist;
import com.apiexample.webflux.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

    private final PlaylistRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll()
                .thenMany(
                        Flux.just(
                                "Playlist 01",
                                "Playlist 02",
                                "Playlist 03",
                                "Playlist 04",
                                "Playlist 05"
                        )
                        .map(nome -> Playlist.builder()
                                .id(UUID.randomUUID().toString())
                                .nome(nome)
                                .build())
                        .flatMap(repository::save))
                .subscribe(System.out::println);
    }
}
