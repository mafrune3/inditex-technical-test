package com.inditex.test.infraestructure.controllers;

import com.inditex.test.application.usecases.GetByDateProductIdAndBrandIdUC;
import com.inditex.test.domain.models.Price;
import com.inditex.test.infraestructure.mappers.PriceMapper;
import com.inditex.test.infraestructure.responses.ErrorResponse;
import com.inditex.test.infraestructure.responses.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get product by date, product id and brand id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found",
                    content = @Content(schema = @Schema(implementation = Price.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/get-by-params")
    public @ResponseBody ResponseEntity<PriceResponse> getByDateParamIdAndBrandId(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam int productId,
            @RequestParam int brandId) {

        Price price = getPriceByRangeUseCase.handle(date, productId, brandId);
        return ResponseEntity.ok(PriceMapper.toResponse(price));
    }

}
