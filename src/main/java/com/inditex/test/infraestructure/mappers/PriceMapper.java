package com.inditex.test.infraestructure.mappers;

import com.inditex.test.domain.models.Price;
import com.inditex.test.infraestructure.entities.PriceEntity;
import com.inditex.test.infraestructure.responses.PriceResponse;

public class PriceMapper {
    public static Price toDomain(PriceEntity priceEntity) {
        return new Price(
                priceEntity.getBrandId(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPriceList(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurrency()
        );
    }

    public static PriceResponse toResponse(Price price) {
        return new PriceResponse(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price()
        );
    }

}
