package com.inditex.test.infraestructure.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetByDateProductIdAndBrandIdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String urlTemplate = "/product/price/get-by-params?date=%s&productId=%d&brandId=%d";
    private final int productId = 35455;
    private final int brandId = 1;

    @Test
    void should_return_price_35_5_and_price_list_1_in_day_14_at_10_00_for_product_35455_and_brand_1() throws Exception {

        String date = "2020-06-14T10:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.5))
                .andReturn();
    }

    @Test
    void should_return_price_25_45_and_price_list_2_in_day_14_at_16_00_for_product_35455_and_brand_1() throws Exception {

        String date = "2020-06-14T16:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andReturn();
    }

    @Test
    void should_return_price_35_5_and_price_list_1_in_day_14_at_21_00_for_product_35455_and_brand_1() throws Exception {

        String date = "2020-06-14T21:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.5))
                .andReturn();
    }

    @Test
    void should_return_price_30_50_and_price_list_3_in_day_15_at_10_00_for_product_35455_and_brand_1() throws Exception {

        String date = "2020-06-15T10:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.5))
                .andReturn();
    }

    @Test
    void should_return_price_38_95_and_price_list_4_in_day_16_at_21_00_for_product_35455_and_brand_1() throws Exception {

        String date = "2020-06-16T21:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andReturn();
    }

    @Test
    void should_return_price_200_75_and_price_list_1_in_day_14_at_10_00_for_product_35455_and_brand_1_in_year_2021() throws Exception {

        String date = "2021-06-14T10:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(200.75))
                .andReturn();
    }

    @Test
    void should_return_badRequest_when_price_not_found() throws Exception {
        String date = "2019-06-16T21:00:00";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("PRICE_NOT_FOUND"))
                .andReturn();
    }

    @Test
    void should_return_badRequest_when_date_format_is_invalid() throws Exception {
        String date = "Not valid date";
        String url = String.format(urlTemplate, date, productId, brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("INVALID_ARGUMENT_TYPE"))
                .andReturn();
    }

    @Test
    void should_return_badRequest_when_productId_format_is_invalid() throws Exception {
        String date = "2019-06-16T21:00:00";
        String urlTemplate = "/product/price/get-by-params?date=%s&productId=%s&brandId=%d";
        String url = String.format(urlTemplate, date, "fail", brandId);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("INVALID_ARGUMENT_TYPE"))
                .andReturn();
    }

    @Test
    void should_return_badRequest_when_brandId_format_is_invalid() throws Exception {
        String date = "2019-06-16T21:00:00";
        String urlTemplate = "/product/price/get-by-params?date=%s&productId=%d&brandId=%s";
        String url = String.format(urlTemplate, date, productId, "fail");

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("INVALID_ARGUMENT_TYPE"))
                .andReturn();
    }

}
