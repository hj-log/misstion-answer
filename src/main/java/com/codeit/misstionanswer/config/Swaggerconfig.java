package com.codeit.misstionanswer.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class Swaggerconfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mission Answer API")
                        .description("정답의 Swagger API 문서입니다.")
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("로컬서버")
                ));
    }
}
