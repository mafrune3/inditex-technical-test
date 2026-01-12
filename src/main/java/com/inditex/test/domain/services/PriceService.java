package com.inditex.test.domain.services;

import com.inditex.test.domain.exceptions.PriceNotFoundException;
import com.inditex.test.domain.models.Price;
import com.inditex.test.domain.ports.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getByDateProductIdAndBrandId(LocalDateTime date, int productId, int brandId) {
        return priceRepository.findByDateProductIdAndBrandId(date, productId, brandId)
                .orElseThrow(PriceNotFoundException::new);
    }
}
