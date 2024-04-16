DELETE
FROM personajes;

INSERT INTO personajes (nombre,tipo,habilidad,ataque,edad,arma,is_deleted)
VALUES ('Test1','Guerreros','Test1',10,10,'Test1',false),
       ('Test2','Guerreros','Test2',20,20,'Test2',false),
       ('Test3','Enemigos','Test3',30,30,'Test3',false),
       ('Test4','Enemigos','Test4',40,40,'Test4',false);