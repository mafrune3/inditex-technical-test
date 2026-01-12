package com.inditex.test.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inditex Technical Test")
                        .version("1.0")
                        .description("Configuration to get price by params")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache-2.0")
                                .url("http://springdoc.org")
                        )
                );
    }

}
