package com.example.eventmanagment.configs;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayMigrationConfig {
    @Bean
    public FlywayMigrationStrategy repairFlyway() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };

    }
}
