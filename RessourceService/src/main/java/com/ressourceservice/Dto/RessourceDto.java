package com.ressourceservice.Dto;


import lombok.Data;

@Data
public class RessourceDto {
    private int idRessource;
    private String nom;
    private String type;
    private int quantite;
    private String fournisseur;
    private int idTache;
}
