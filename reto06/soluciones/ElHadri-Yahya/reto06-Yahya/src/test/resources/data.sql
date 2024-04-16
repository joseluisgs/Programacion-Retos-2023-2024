DELETE
FROM personajes;

INSERT INTO personajes (nombre,tipo,habilidad,ataque,edad,arma,is_deleted)
VALUES
    ('Test 1','Guerrero','Test 1',55,68,'Test 1',0),
    ('Test 2','Guerrero','Test 2',55,68,'Test 2',0),
    ('Test 3','Enemigo','Test 3',55,68,'Test 3',0),
    ('Test 4','Enemigo','Test 4',55,68,'Test 4',0);
