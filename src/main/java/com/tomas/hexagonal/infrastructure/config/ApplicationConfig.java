package com.tomas.hexagonal.infrastructure.config;

import com.tomas.hexagonal.application.usecases.TaskServiceImpl;
import com.tomas.hexagonal.domain.ports.in.TaskUseCase;
import com.tomas.hexagonal.domain.ports.out.TaskRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskUseCase taskUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new TaskServiceImpl(taskRepositoryPort);
    }
}
