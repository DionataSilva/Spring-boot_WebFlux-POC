package com.apiexample.webflux.service.quotation;

import reactor.core.publisher.Flux;

public interface QuotationService {
    Flux<Object> getQuotation(String provider);
}
