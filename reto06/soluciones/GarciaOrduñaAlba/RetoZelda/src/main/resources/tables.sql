CREATE TABLE IF NOT EXISTS personaje (
                                        id INTEGER PRIMARY KEY,
                                        tipo TEXT NOT NULL,
                                        nombre TEXT NOT NULL,
                                        habilidad TEXT NOT NULL,
                                        ataque TEXT NOT NULL,
                                        edad INTEGER default NULL,
                                        arma TEXT NOT NULL,
                                        createdAt TEXT NOT NULL,
                                        updatedAt TEXT NOT NULL,
                                        isDeleted INTEGER default 0
);