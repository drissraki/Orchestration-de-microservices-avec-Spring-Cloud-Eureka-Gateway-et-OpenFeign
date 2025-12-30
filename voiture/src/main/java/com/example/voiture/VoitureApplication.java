package com.example.voiture;

import com.example.voiture.entities.Voiture;
import com.example.voiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class VoitureApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }
    
    @Bean
    CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository) {
        return args -> {
            voitureRepository.save(new Voiture(null, "Toyota", "Corolla", "A12345", 1L));
            voitureRepository.save(new Voiture(null, "Renault", "Megane", "B67890", 2L));
            voitureRepository.save(new Voiture(null, "Peugeot", "208", "C54321", 1L));
        };
    }
}
