package com.devptl.school_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI schoolOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("School Microservice API")
                        .description("Documentation for School Service endpoints")
                        .version("v1.0.0"));
    }
}
