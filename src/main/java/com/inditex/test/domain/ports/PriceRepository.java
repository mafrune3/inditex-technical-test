package com.inditex.test.domain.ports;

import com.inditex.test.domain.models.Price;

import java.time.LocalDateTime;

public interface PriceRepository {
    Price findByDateProductIdAndBrandId(LocalDateTime date, int productId, int brandId);
}
