CREATE TABLE IF NOT EXISTS personajes (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    tipo TEXT NOT NULL,
    clase TEXT default NULL,
    habilidad TEXT default NULL,
    ataque INTEGER default NULL,
    edad INTEGER default NULL,
    arma TEXT default NULL,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    is_deleted INTEGER default 0
);