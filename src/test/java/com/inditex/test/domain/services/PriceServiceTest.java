package com.inditex.test.domain.services;

import com.inditex.test.domain.exceptions.PriceNotFoundException;
import com.inditex.test.domain.models.Price;
import com.inditex.test.domain.ports.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    private static final LocalDateTime DATE = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private static final int PRODUCT_ID = 35455;
    private static final int BRAND_ID = 1;

    private Price expectedPrice;

    @BeforeEach
    void setUp() {
        expectedPrice = new Price(
                BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                PRODUCT_ID,
                0,
                35.50F,
                "EUR"
        );
    }

    @Test
    void should_returnPrice_whenFound() {
        when(priceRepository.findByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID))
                .thenReturn(Optional.of(expectedPrice));

        Price actualPrice = priceService.getByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID);

        assertNotNull(actualPrice);
        assertEquals(expectedPrice.productId(), actualPrice.productId());
        assertEquals(expectedPrice.brandId(), actualPrice.brandId());

        verify(priceRepository, times(1)).findByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID);
    }

    @Test
    void should_throwException_whenNotFound() {
        when(priceRepository.findByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> {
            priceService.getByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID);
        });

        verify(priceRepository, times(1)).findByDateProductIdAndBrandId(DATE, PRODUCT_ID, BRAND_ID);
    }
}
