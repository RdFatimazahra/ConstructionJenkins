CREATE TABLE ressources (
                            idRessource SERIAL PRIMARY KEY,
                            nom VARCHAR(255) ,
                            type VARCHAR(50),
                            quantite INT,
                            fournisseur VARCHAR(50),
                            id_tache BIGINT NOT NULL
);

