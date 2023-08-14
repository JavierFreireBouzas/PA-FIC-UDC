DROP TABLE Compra;
DROP TABLE Sesion;
DROP TABLE Sala;
DROP TABLE Pelicula;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE TABLE Pelicula (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(60) NOT NULL,
    resumen VARCHAR(2000) NOT NULL,
    duracion SMALLINT NOT NULL,
    CONSTRAINT PeliculaPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Sala (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombreSala VARCHAR(60) NOT NULL,
    capacidad SMALLINT NOT NULL,
    CONSTRAINT SalaPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Sesion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    localidadesLibres INT NOT NULL,
    version BIGINT NOT NULL,
    peliculaId BIGINT NOT NULL,
    salaId BIGINT NOT NULL,
    fecha DATETIME NOT NULL,
    precio DECIMAL(11, 2) NOT NULL,
    CONSTRAINT SesionPK PRIMARY KEY (id),
    CONSTRAINT SesionPeliculaIdFK FOREIGN KEY(peliculaId)
        REFERENCES Pelicula (id),
    CONSTRAINT SesionSalaIdFK FOREIGN KEY(salaId)
        REFERENCES Sala (id)
) ENGINE = InnoDB;

CREATE TABLE Compra (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    sesionId BIGINT NOT NULL,
    fecha DATETIME NOT NULL,
    numLocalidades TINYINT NOT NULL,
    tarjetaBancaria VARCHAR(19) NOT NULL,
    entregadas BIT NOT NULL,
    CONSTRAINT CompraPK PRIMARY KEY (id),
    CONSTRAINT CompraUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id),
    CONSTRAINT SesionIdFK FOREIGN KEY(sesionId)
        REFERENCES Sesion (id)
) ENGINE = InnoDB;
