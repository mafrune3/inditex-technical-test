package com.inditex.test.infraestructure.controllers;

import com.inditex.test.application.GetByDateProductIdAndBrandIdUC;
import com.inditex.test.domain.models.Price;
import com.inditex.test.infraestructure.mappers.PriceMapper;
import com.inditex.test.infraestructure.responses.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product/price")
public class GetByDateProductIdAndBrandIdController {

    public final GetByDateProductIdAndBrandIdUC getPriceByRangeUseCase;

    public GetByDateProductIdAndBrandIdController(GetByDateProductIdAndBrandIdUC getPriceByRangeUseCase) {
        this.getPriceByRangeUseCase = getPriceByRangeUseCase;
    }

    @GetMapping("/get-by-params")
    public @ResponseBody ResponseEntity<PriceResponse> getByDateParamIdAndBrandId(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam int productId,
            @RequestParam int brandId) {

        Price price = getPriceByRangeUseCase.handle(date, productId, brandId);
        return ResponseEntity.ok(PriceMapper.toResponse(price));
    }

}
