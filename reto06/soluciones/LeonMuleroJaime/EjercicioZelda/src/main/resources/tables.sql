CREATE TABLE IF NOT EXISTS personajes (
    tipo TEXT NOT NULL,
    nombre TEXT NOT NULL PRIMARY KEY,
    habilidad TEXT NOT NULL,
    ataque INTEGER NOT NULL,
    edad INTEGER NOT NULL,
    arma TEXT NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER default 0
);