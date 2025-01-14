package com.ulloa.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .uri("lb://security-service"))
                .route(r -> r.path("/products/**")
                        .uri("lb://product-service"))
                .route(r -> r.path("/auth/**")
                        .uri("lb://order-service"))
                .route(r -> r.path("/orders/**")
                        .uri("lb://payment-service"))
                .route(r -> r.path("/payments/**")
                        .uri("lb://customer-service"))
                .route(r -> r.path("/customers/**")
                        .uri("lb://security-service"))
                .build();
    }

}
