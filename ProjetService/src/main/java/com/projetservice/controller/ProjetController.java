package com.projetservice.controller;

import com.projetservice.client.TacheClient;
import com.projetservice.dto.projetDto;
import com.projetservice.model.FullProjectResponse;
import com.projetservice.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {

    @Autowired
    private ProjetService projetService;
    @Autowired
    TacheClient tacheClient;

    @PostMapping
    public ResponseEntity<projetDto> createProjet(@RequestBody projetDto projetDto) {
        projetDto createdProjet = projetService.createProjet(projetDto);
        return ResponseEntity.ok(createdProjet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<projetDto> getProjetById(@PathVariable int id) {
        projetDto projetDto = projetService.getProjetById(id);
        return ResponseEntity.ok(projetDto);
    }

    @GetMapping("/gateway/{id}")
    public ResponseEntity<FullProjectResponse> projetWithTaches(@PathVariable int id) {
        FullProjectResponse projetDto = projetService.projetWithTaches(id);
        return ResponseEntity.ok(projetDto);
    }

    @GetMapping
    public ResponseEntity<List<projetDto>> getAllProjets() {
        List<projetDto> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<projetDto> updateProjet(@PathVariable int id, @RequestBody projetDto projetDto) {
        projetDto updatedProjet = projetService.updateProjet(id, projetDto);
        return ResponseEntity.ok(updatedProjet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable int id) {
//        tacheClient.deleteTachesByProjetId(id);
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
}
