package com.inditex.test.domain.models;

import java.time.LocalDateTime;

public record Price(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        Float price,
        String currency
) {
}
