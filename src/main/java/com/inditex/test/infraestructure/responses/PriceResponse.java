package com.inditex.test.infraestructure.responses;

import java.time.LocalDateTime;

public record PriceResponse(Integer productId, Integer brandId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate, Float price) {
}

