package com.apiexample.webflux.service.quotation;

import com.apiexample.webflux.model.Quotation;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

@Service
@Data
public class QuotationServiceImpl implements QuotationService {

    private static final Random random = new Random();
    private static final Duration randomInterval = Duration.ofMillis(random.nextInt(3000));

    @Override
    public Flux<Object> getQuotation(String provider) {
//        return withInterval(provider);
        return withOutInterval(provider);
    }

    private Flux<Object> withInterval(String provider) {
        var quotationList = new ArrayList<Quotation>();

        for (int i=0; i<5; i++) {
            var quotation = calculateAndBuildQuotation(provider);
            quotationList.add(quotation);
        }

        return Flux.interval(randomInterval)
                .zipWithIterable(quotationList)
                .map(Tuple2::getT2);
    }

    private Flux<Object> withOutInterval(String provider) {
        return Flux.range(1, 5)
                .map(i -> calculateAndBuildQuotation(provider));
    }


    private Quotation calculateAndBuildQuotation(String provider) {
        var randomValue = 10 + (99-10) * random.nextDouble();

        var price = BigDecimal
                .valueOf(randomValue)
                .setScale(2, RoundingMode.HALF_UP);

        return Quotation.builder()
                .companyName(provider)
                .price(price)
                .build();
    }

}
