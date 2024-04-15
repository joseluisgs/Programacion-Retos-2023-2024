CREATE TABLE IF NOT EXISTS personajes (
    id INTEGER NOT NULL PRIMARY KEY,
    tipo TEXT NOT NULL,
    nombre TEXT NOT NULL,
    habilidad TEXT NOT NULL,
    ataque INTEGER NOT NULL,
    edad INTEGER NOT NULL,
    arma TEXT NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER default 0
);