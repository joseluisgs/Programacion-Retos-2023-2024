CREATE TABLE IF NOT EXISTS personajes (
    nombre TEXT NOT NULL PRIMARY KEY,
    tipo TEXT NOT NULL,
    habilidad TEXT NOT NULL,
    ataque INTEGER NOT NULL,
    edad INTEGER default NULL,
    arma TEXT NOT NULL,
    is_deleted INTEGER default 0
);