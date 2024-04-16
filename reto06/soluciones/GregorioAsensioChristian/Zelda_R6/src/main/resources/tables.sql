CREATE TABLE IF NOT EXISTS personajes (
    id INTEGER NOT NULL PRIMARY KEY,
    tipo TEXT NOT NULL,
    nombre TEXT NOT NULL,
    habilidad TEXT NOT NULL,
    ataque INTEGER NOT NULL,
    edad INTEGER NOT NULL,
    arma TEXT NOT NULL,
    createdAt TEXT NOT NULL,
    updatedAt TEXT NOT NULL,
    isDeleted INTEGER default 0
);