package edu.lms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("LMS API's Documentation")
                .version("1.0")
                .contact(new Contact()
                        .name("Luke")
                        .email("Luke@example.com")
                        .url("https://github.com/se-fu/lms")
                )
                .description("Luke")
                .license(new License()
                        .name("FU-LMS")
                        .url("https://github.com/se-fu/lms")
                )

        );
    }
}
