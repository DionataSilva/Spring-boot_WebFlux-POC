package com.apiexample.webflux.controller.quotation;

import com.apiexample.webflux.model.Quotation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class QuotationHandler {

    private static final Random random = new Random();

    public Mono<ServerResponse> getQuotation(ServerRequest serverRequest) {
        Flux<Object> result = Flux.merge(
                getQuotationValue("Fornecedor 1"),
                getQuotationValue("Fornecedor 2"),
                getQuotationValue("Fornecedor 3"),
                getQuotationValue("Fornecedor 4")
        );
        return ServerResponse.ok()
                .header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(result, Quotation.class);
    }

    private Flux<Object> getQuotationValue(String provider) {
        var quotationList = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int i=0; i<5; i++) {
            double randomValue = 10 + (99-10) * random.nextDouble();
            BigDecimal valor = BigDecimal.valueOf(randomValue)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            result.add(provider + " - " + valor);
            quotationList.add(Quotation.builder().companyName(provider).price(valor).build());
        }

        return Flux.interval(Duration.ofMillis(random.nextInt(3000)))
                .zipWithIterable(quotationList)
                .map(Tuple2::getT2);
    }
}
