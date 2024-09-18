package com.tacheservice.repository;

import com.tacheservice.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Integer> {

    List<Tache> findByIdProjet(int projetId);
//    @Query("Select Tache as t, count(idRessource) as total from Tache where statut ='à faire' Group by idTache")
//    List<Tache> findByStatus();
}
