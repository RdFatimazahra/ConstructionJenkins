package com.tacheservice.controller;


import com.tacheservice.dto.TacheDto;
import com.tacheservice.model.FullTachesResponse;
import com.tacheservice.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;


    // Créer une nouvelle tâche Utilisant DTO
    @PostMapping("/projet/{projetId}")
    public ResponseEntity<TacheDto> createTache(@PathVariable int projetId, @RequestBody TacheDto dto) {
        TacheDto createdTache = tacheService.createTache(dto,projetId);
        return ResponseEntity.ok().body(createdTache);
    }

//CreateTache method whitout using DTO
//    @PostMapping("/projet/{projetId}")
//    public ResponseEntity<tache> createTache(@PathVariable int projetId, @RequestBody tache tacheDto) {
//        // Assigner l'ID du projet au DTO avant de créer la tâche
//        tacheDto.setIdProjet(projetId);
//        tache createdTache = tacheService.createTache(tacheDto, projetId);
//        return ResponseEntity.ok(createdTache);
//    }

    @GetMapping
    public ResponseEntity<List<TacheDto>> getAllTaches() {
        List<TacheDto> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacheDto> getTacheById(@PathVariable int id) {
        TacheDto tacheDto = tacheService.getTacheById(id);
        return ResponseEntity.ok(tacheDto);
    }

    @GetMapping("/gateway/{id}")
    public ResponseEntity<List<TacheDto>> tachesOfProjet(@PathVariable int id) {
        List<TacheDto> tacheDto = tacheService.getTachesByProjet(id);
        return ResponseEntity.ok(tacheDto);
    }

    @GetMapping("/gate/{id}")
    public ResponseEntity <FullTachesResponse> tachesWithRessources(@PathVariable int id) {
        FullTachesResponse fullTachesResponse = tacheService.tachWithRessources(id);
        return ResponseEntity.ok(fullTachesResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TacheDto> updateTache(@PathVariable int id, @RequestBody TacheDto tacheDto) {
        TacheDto updatedTache = tacheService.updateTache(id, tacheDto);
        return ResponseEntity.ok(updatedTache);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable int id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/projet/{idProjet}")
    public void deleteTachesByProjetId(@PathVariable int idProjet) {
        tacheService.deleteTachesByProjetId(idProjet);
    }

}

