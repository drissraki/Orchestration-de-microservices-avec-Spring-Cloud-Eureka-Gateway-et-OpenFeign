package com.example.voiture.controllers;

import com.example.voiture.entities.Voiture;
import com.example.voiture.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureController {
    @Autowired
    VoitureRepository voitureRepository;

    @GetMapping("/voitures")
    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    @GetMapping("/voitures/{id}")
    public Voiture findById(@PathVariable Long id) throws Exception {
        return voitureRepository.findById(id)
            .orElseThrow(() -> new Exception("Voiture non trouvée"));
    }
    
    @GetMapping("/voitures/client/{clientId}")
    public List<Voiture> findByClient(@PathVariable Long clientId) {
        return voitureRepository.findByClientId(clientId);
    }
}
