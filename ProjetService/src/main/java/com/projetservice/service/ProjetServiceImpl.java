package com.projetservice.service;

import com.projetservice.client.TacheClient;
import com.projetservice.dto.projetDto;
import com.projetservice.mapper.ProjetMapper;
import com.projetservice.model.FullProjectResponse;
import com.projetservice.model.Taches;
import com.projetservice.model.Projet;
import com.projetservice.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private ProjetMapper projetMapper;

    @Autowired
    TacheClient tacheClient;

    @Override
    public projetDto createProjet(projetDto projetDto) {
        Projet projetEntity = projetMapper.projetDtoToProjet(projetDto);
        Projet savedProjet = projetRepository.save(projetEntity);
        return projetMapper.projetToProjetDto(savedProjet);
    }

    @Override
    public projetDto getProjetById(int id) {
        Projet projetEntity = projetRepository.findById(id).orElse(null);
        return projetMapper.projetToProjetDto(projetEntity);
    }

    @Override
    public List<projetDto> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        return projets.stream()
                .map(projetMapper::projetToProjetDto)
                .collect(Collectors.toList());
    }

    @Override
    public projetDto updateProjet(int id, projetDto projetDto) {
        Projet existingProjet = projetRepository.findById(id).orElse(null);
        if (existingProjet != null) {
            Projet updatedProjet = projetMapper.projetDtoToProjet(projetDto);
            updatedProjet.setIdProjet(id); // Ensure the ID remains the same
            projetRepository.save(updatedProjet);
            return projetMapper.projetToProjetDto(updatedProjet);
        }
        return null;
    }

    @Override
    // Supprimer un projet existant
    public void deleteProjet(int id) {

        try {
            // D'abord, supprimer les tâches liées à ce projet
            tacheClient.deleteTachesByProjetId(id);
        } catch (Exception e) {
            throw new IllegalStateException("Erreur lors de la suppression des tâches pour l'ID du projet : " + id, e);
        }

        // Ensuite, supprimer le projet
        projetRepository.deleteById(id);
    }


    @Override
    public FullProjectResponse projetWithTaches(int id) {
        Projet projets = projetRepository.findById(id)
                .orElse(
                        Projet.builder()
                                .nomProjet("NOT_FOUND")
                                .build()
                );
        List<Taches> taches = tacheClient.findAllTachesByProjet(id);
        return FullProjectResponse.builder()
                .nomProjet(projets.getNomProjet())
                .dateDebut(projets.getDateDebut())
                .dateFin(projets.getDateFin())
                .description(projets.getDescription())
                .budget(projets.getBudget())
                .taches(taches)
                .build();
    }


}

