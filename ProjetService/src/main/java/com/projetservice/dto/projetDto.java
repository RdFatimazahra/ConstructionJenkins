package com.projetservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class projetDto {

    private int idProjet;
    private String nomProjet;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private double budget;
}
