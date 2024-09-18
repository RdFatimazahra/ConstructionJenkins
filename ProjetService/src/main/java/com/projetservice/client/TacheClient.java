package com.projetservice.client;


import com.projetservice.model.Taches;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "tache-service", url = "${tache-service.url}")
public interface TacheClient {

    @GetMapping("/gateway/{id}")
    List<Taches> findAllTachesByProjet(@PathVariable int id);
    @DeleteMapping("/projet/{idProjet}")
    void deleteTachesByProjetId(@PathVariable int idProjet);
//    @DeleteMapping("/projet/delete/{id}")
//    List<Taches> deleteTacheWithProjet(@PathVariable Long id);
}
