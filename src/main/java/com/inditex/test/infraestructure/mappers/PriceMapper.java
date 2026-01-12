package com.inditex.test.infraestructure.mappers;

import com.inditex.test.domain.models.Price;
import com.inditex.test.infraestructure.entities.PriceEntity;

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
}
