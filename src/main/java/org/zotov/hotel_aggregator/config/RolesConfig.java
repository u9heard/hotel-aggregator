package org.zotov.hotel_aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RolesConfig {

    @Bean
    protected Map<String, Integer> accessLevels(){
        return Map.of("/admin", 10, "/hotels", 1);
    }
}
