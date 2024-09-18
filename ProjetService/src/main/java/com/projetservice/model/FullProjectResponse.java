package com.projetservice.model;


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
public class FullProjectResponse {
    private int idProjet;
    private String nomProjet;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private double budget;
    List<Taches> taches;

}
