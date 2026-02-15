package com.example.TaskManager.Task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class swagger {

    @Bean
    public OpenAPI api() {
        log.info("SwaggerBean WAS CREATED");
        return new OpenAPI()
                .servers(
                        List.of(new Server().url("http://localhost:8080"))
                )
                .info(new Info().title("TaskManagerAPI"));
    }
}
