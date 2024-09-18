package com.ressourceservice.repository;

import com.ressourceservice.model.ressources;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RessourceRepository extends CrudRepository<ressources, Integer> {

    List<ressources> findByIdTache(int idTache);
}
