package com.inditex.test.infraestructure.repositories;

import com.inditex.test.infraestructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceJPARepository extends JpaRepository<PriceEntity, Integer> {
    Optional<PriceEntity> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime date,
            LocalDateTime date2,
            int productId,
            int brandId
    );
}
