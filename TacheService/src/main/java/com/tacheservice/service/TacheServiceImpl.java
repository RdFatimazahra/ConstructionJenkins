package com.tacheservice.service;

import com.tacheservice.Client.RessourcesClient;
import com.tacheservice.ResourceNotFoundException;
import com.tacheservice.dto.TacheDto;
import com.tacheservice.mapper.TacheMapper;
import com.tacheservice.model.FullTachesResponse;
import com.tacheservice.model.Ressources;
import com.tacheservice.model.Tache;
import com.tacheservice.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TacheServiceImpl implements TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TacheMapper tacheMapper;

    @Autowired
    RessourcesClient ressourcesClient;

    @Override

    //CreateTache method using DTO
    public  TacheDto createTache(TacheDto tacheDto, int idProjet) {
        restTemplate.getForObject("http://localhost:8090/api/projets/" + idProjet, Object.class);
        Tache tache = tacheMapper.tacheDtoToTache(tacheDto);
        tache.setIdProjet(idProjet);
        Tache savedTache = tacheRepository.save(tache);
        return tacheMapper.tacheToTacheDto(savedTache);

    }

    //CreateTache whithout using DTO
//    public tache createTache(tache tache, int idProjet) {
//        try {
//            restTemplate.getForObject("http://localhost:8081/api/projets/" + idProjet, Object.class);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("projet non trouve :" + e);
//        }
//        tache.setIdProjet(idProjet);
//        return tacheRepository.save(tache);
//    }
    @Override
    public List<TacheDto> getAllTaches() {
        List<Tache> taches = tacheRepository.findAll();
        return taches.stream()
                .map(tacheMapper::tacheToTacheDto)
                .collect(Collectors.toList());
    }

    @Override
    public TacheDto getTacheById(int id) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tache not found with id: " + id));

        return tacheMapper.tacheToTacheDto(tache);
    }

    @Override
    public TacheDto updateTache(int id, TacheDto tacheDto) {
        Tache existingTache = tacheRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tache not found with id: " + id));

        // Convert DTO to entity
        Tache updatedTache = tacheMapper.tacheDtoToTache(tacheDto);
        updatedTache.setIdTache(id); // Ensure the ID is set

        // Save the updated entity
        Tache savedTache = tacheRepository.save(updatedTache);

        // Convert entity back to DTO
        return tacheMapper.tacheToTacheDto(savedTache);
    }

    @Override
    public void deleteTache(int id) {
        Tache existingTache = tacheRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tache not found with id: " + id));

        tacheRepository.delete(existingTache);
    }

    @Override
    public List<TacheDto> getTachesByProjet(int idProjet) {
        List<Tache> taches = tacheRepository.findByIdProjet(idProjet);
        return taches.stream()
                .map(tacheMapper::tacheToTacheDto)
                .collect(Collectors.toList());
    }

    @Override
    public FullTachesResponse tachWithRessources(int id) {
        Tache taches = tacheRepository.findById(id).orElse(
                Tache.builder()
                        .description("NOT_FOUND")
                        .build()
        );
        List<Ressources> ressources = ressourcesClient.getRessourcesByTache(id);
        return FullTachesResponse.builder()
                .description(taches.getDescription())
                .dateDebut(taches.getDateDebut())
                .dateFin(taches.getDateFin())
                .statut(taches.getStatut())
                .ressources(ressources)
                .build();

    }

    public void deleteTachesByProjetId(int idProjet) {
        List<Tache> taches = tacheRepository.findByIdProjet(idProjet);
        if (taches != null && !taches.isEmpty()) {
            tacheRepository.deleteAll(taches);
        }
    }
}