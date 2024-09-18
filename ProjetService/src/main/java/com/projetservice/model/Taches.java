package com.projetservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taches {
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
}