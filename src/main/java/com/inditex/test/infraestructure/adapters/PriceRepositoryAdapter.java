package com.inditex.test.infraestructure.adapters;

import com.inditex.test.domain.exceptions.PriceNotFoundException;
import com.inditex.test.domain.models.Price;
import com.inditex.test.domain.ports.PriceRepository;
import com.inditex.test.infraestructure.mappers.PriceMapper;
import com.inditex.test.infraestructure.repositories.PriceJPARepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class PriceRepositoryAdapter implements PriceRepository {
    private final PriceJPARepository jpaRepository;

    public PriceRepositoryAdapter(PriceJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Price findByDateProductIdAndBrandId(LocalDateTime date, int productId, int brandId) {
        return jpaRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, date, productId, brandId)
                .map(PriceMapper::toDomain)
                .orElseThrow(PriceNotFoundException::new);
    }
}
