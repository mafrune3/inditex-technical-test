package com.inditex.test.domain.ports;

import com.inditex.test.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findByDateProductIdAndBrandId(LocalDateTime date, int productId, int brandId);
}
