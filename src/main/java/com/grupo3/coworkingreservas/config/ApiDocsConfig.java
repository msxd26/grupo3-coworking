package com.grupo3.coworkingreservas.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocsConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Coworking")
                        .description("Gestion de Salas y Reservas para Colaboracion")
                        .version("1.0.0"));
    }
}