package com.ressourceservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class ressources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRessource;
    private String nom;
    private String type;
    private int quantite;
    private String fournisseur;
    private int idTache;

}
