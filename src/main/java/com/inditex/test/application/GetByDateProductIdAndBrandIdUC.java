package com.inditex.test.application;

import com.inditex.test.domain.models.Price;
import com.inditex.test.domain.services.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetByDateProductIdAndBrandIdUC {
    private final PriceService priceService;

    public GetByDateProductIdAndBrandIdUC(PriceService priceService) {
        this.priceService = priceService;
    }

    public Price handle(LocalDateTime date, int productId, int stringIdentifier) {

        return priceService.getByDateProductIdAndBrandId(date, productId, stringIdentifier);

    }
}
