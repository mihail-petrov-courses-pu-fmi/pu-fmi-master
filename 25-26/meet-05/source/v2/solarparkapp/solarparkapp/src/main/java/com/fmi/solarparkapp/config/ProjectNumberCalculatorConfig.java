package com.fmi.solarparkapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectNumberCalculatorConfig {

    //     Фабрика за генериране на имплементации
    // --- стратегия
    // вариант едно ТЕСТОВИ алгоритъм за количество проекти
    // вариант две DEV алгоритъм за количество проекти

    @Bean
    @ConditionalOnProperty(name = "solar.provider.type", havingValue = "dev", matchIfMissing = true)
    public ProjectCalculatorProvider devCalculator() {
        return new DevProjectCalculator();
    }

    @Bean
    @ConditionalOnProperty(name="solar.provider.type", havingValue = "prod")
    public ProjectCalculatorProvider prodCalculator() {
        return new ProdProjectCalculator();
    }
}
