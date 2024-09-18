CREATE TABLE projet (
                        id_projet SERIAL PRIMARY KEY,
                        nom_projet VARCHAR(255) NOT NULL,
                        description TEXT,
                        date_debut DATE,
                        date_fin DATE,
                        budget DECIMAL(10, 2)
);

