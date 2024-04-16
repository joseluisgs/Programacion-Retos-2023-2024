CREATE TABLE IF NOT EXISTS personajes (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    tipo TEXT NOT NULL,
    clase TEXT default NULL,
    habilidad TEXT default NULL,
    ataque INTEGER default NULL,
    edad INTEGER default NULL,
    arma TEXT default NULL,
    created_at TEXT DEFAULT (datetime('now', 'localtime')),
    updated_at TEXT DEFAULT (datetime('now', 'localtime')),
    is_deleted INTEGER default 0
);