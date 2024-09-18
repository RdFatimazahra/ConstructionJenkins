package com.tacheservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullTachesResponse {
    private int idTache;
    private int idProjet;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    public List<Ressources> ressources ;
}
