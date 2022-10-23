package com.apiexample.webflux.controller.playlist;

import com.apiexample.webflux.model.Playlist;
import com.apiexample.webflux.service.playlist.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PlaylistHandler {

    private final PlaylistService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        var id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<Playlist> playlistMono = request.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlistMono.flatMap(service::save), Playlist.class));
    }

}
