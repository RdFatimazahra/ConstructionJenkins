package com.tacheservice.Client;

import com.tacheservice.model.Ressources;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ressource-service", url = "${ressource-service.url}")
public interface RessourcesClient {
    @GetMapping("/tache/{id}")
    List<Ressources> getRessourcesByTache(@PathVariable int id);
//    @DeleteMapping("tache/{id}")
//    void deleteRessourcesByTache(@PathVariable Long id);
}
