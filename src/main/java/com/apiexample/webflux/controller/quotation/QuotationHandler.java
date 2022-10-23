package com.apiexample.webflux.controller.quotation;

import com.apiexample.webflux.model.Quotation;
import com.apiexample.webflux.service.quotation.QuotationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class QuotationHandler {

    private final QuotationServiceImpl quotationService;

    public Mono<ServerResponse> getQuotation(ServerRequest serverRequest) {
        Flux<Object> result = Flux.merge(
                quotationService.getQuotation("Fornecedor 1"),
                quotationService.getQuotation("Fornecedor 2"),
                quotationService.getQuotation("Fornecedor 3"),
                quotationService.getQuotation("Fornecedor 4")
        );

        return ServerResponse.ok()
                .header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(result, Quotation.class);
    }
}
