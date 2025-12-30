package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    
    @GetMapping("/")
    public String home() {
        return """
                <html>
                <head><title>API Gateway</title></head>
                <body>
                    <h1>Welcome to the Microservices Gateway</h1>
                    <h2>Available Routes:</h2>
                    <ul>
                        <li><a href="/clients">/clients</a> - Get all clients</li>
                        <li><a href="/client/1">/client/{id}</a> - Get client by ID</li>
                    </ul>
                    <p>Gateway is running on port 8888</p>
                </body>
                </html>
                """;
    }
    
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            // Static routes
            .route(r -> r.path("/clients/**").uri("lb://SERVICE-CLIENT"))
            .route(r -> r.path("/client/**").uri("lb://SERVICE-CLIENT"))
            .route(r -> r.path("/voitures/**").uri("lb://SERVICE-VOITURE"))
            // Dynamic routes with path rewriting
            .route(r -> r.path("/SERVICE-CLIENT/**")
                .filters(f -> f.rewritePath("/SERVICE-CLIENT/(?<segment>.*)", "/${segment}"))
                .uri("lb://SERVICE-CLIENT"))
            .route(r -> r.path("/SERVICE-VOITURE/**")
                .filters(f -> f.rewritePath("/SERVICE-VOITURE/(?<segment>.*)", "/${segment}"))
                .uri("lb://SERVICE-VOITURE"))
            .build();
    }
}
