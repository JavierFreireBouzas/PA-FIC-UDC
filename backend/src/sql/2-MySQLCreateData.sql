-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-- ----------------------------------------------------------------------------

-- Usuarios
INSERT INTO User(userName, password, firstName, lastName, email, role)
VALUES('viewer', '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG',
       'Viewer', 'User', 'viewer@user.com', 0);

INSERT INTO User(userName, password, firstName, lastName, email, role)
VALUES('ticketseller', '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG',
       'Ticketseller', 'User', 'ticketseller@user.com', 1);

-- Salas
INSERT INTO Sala(nombreSala, capacidad)
VALUES('Sala1', 20);

INSERT INTO Sala(nombreSala, capacidad)
VALUES('Sala2', 9);

-- Películas
INSERT INTO Pelicula(titulo,resumen,duracion)
VALUES ('Ratatouille-1',
        'Las peripecias de una rata que se adentra en el mundo de la cocina en la ciu\
        dad de Paris', 111);

INSERT INTO Pelicula(titulo,resumen,duracion)
VALUES('Shutter Island-2',
       'Un detective se adentra en una isla para investigar los extraños incidentes\
       que se dan en ella', 138);

-- Sesiones Hoy
INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE), 10, 14);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE), 15, 9);

-- Sesiones Distintas Hoy
INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '1 12:00' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '1 16:00' DAY_MINUTE), 20, 9);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '2 12:00' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '2 15:00' DAY_MINUTE), 20, 9);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '3 12:30' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '3 18:00' DAY_MINUTE), 20, 9);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '4 11:30' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '4 16:45' DAY_MINUTE), 20, 9);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '5 12:20' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '5 19:00' DAY_MINUTE), 20, 9);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(1, 1, 0, DATE_ADD(DATE(NOW()), INTERVAL '6 12:45' DAY_MINUTE), 12, 20);

INSERT INTO Sesion(peliculaId, salaId, version, fecha, precio, localidadesLibres)
VALUES(2, 2, 0, DATE_ADD(DATE(NOW()), INTERVAL '6 17:15' DAY_MINUTE), 20, 9);



-- Compras
INSERT INTO Compra(userId, sesionId, fecha, numLocalidades, tarjetaBancaria, entregadas)
VALUES(1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 00:00' DAY_MINUTE), 2, 1234567891234567, false);

INSERT INTO Compra(userId, sesionId, fecha, numLocalidades, tarjetaBancaria, entregadas)
VALUES(1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 00:02' DAY_MINUTE), 4, 1234567891234567, false);

INSERT INTO Compra(userId, sesionId, fecha, numLocalidades, tarjetaBancaria, entregadas)
VALUES(1, 3, DATE_ADD(DATE(NOW()), INTERVAL '0 11:00' DAY_MINUTE), 1, 1234567891234567, false);