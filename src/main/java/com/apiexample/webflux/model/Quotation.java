package com.apiexample.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Document
public class Quotation {

    private String companyName;
    private BigDecimal price;

}
