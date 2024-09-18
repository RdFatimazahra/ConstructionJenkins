CREATE TABLE tache (
                       id_tache SERIAL PRIMARY KEY,
                       id_projet BIGINT NOT NULL,
                       description TEXT,
                       date_debut DATE,
                       date_fin DATE,
                       statut VARCHAR(20) CHECK (statut IN ('à faire', 'en cours', 'terminé'))
);
