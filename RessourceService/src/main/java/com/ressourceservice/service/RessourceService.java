package com.ressourceservice.service;

import com.ressourceservice.Dto.RessourceDto;

import java.util.List;

public interface RessourceService {
    RessourceDto createRessource(RessourceDto ressourceDto, int idTache);
    RessourceDto getRessourceById(int id);
    List<RessourceDto> getAllRessources();
    List<RessourceDto> getRessourcesByTacheId(int idTache);
    RessourceDto updateRessource(int id, RessourceDto ressourceDto);
    void deleteRessource(int id);
}
