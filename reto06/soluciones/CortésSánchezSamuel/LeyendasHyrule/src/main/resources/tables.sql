CREATE TABLE IF NOT EXISTS personaje (
    nombre TEXT PRIMARY KEY,
    habilidad TEXT NOT NULL,
    ataque INTEGER NOT NULL,
    edad INTEGER default NULL,
    arma TEXT NOT NULL
);
