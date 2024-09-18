package com.projetservice.service;

import com.projetservice.dto.projetDto;
import com.projetservice.model.FullProjectResponse;

import java.util.List;

public interface ProjetService {

        projetDto createProjet(projetDto projetDto);
        projetDto getProjetById(int id);
        List<projetDto> getAllProjets();
        projetDto updateProjet(int id, projetDto projetDto);
        void deleteProjet(int id);
        FullProjectResponse projetWithTaches(int id);
    }

