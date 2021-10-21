-- Programador: Brian Carrillo
-- Control de versiones
-- Fecha de creacion 20/10/2021

DROP DATABASE IF EXISTS DBCarpoolingGT;
CREATE DATABASE DBCarpoolingGt;
USE DBCarpoolingGt;

CREATE TABLE Carros (
	codigoCarro int not null auto_increment,
    marca varchar(10) not null,
    modelo varchar(10) not null,
    cantAsientos int not null,
    
    primary key PK_codigoCarro (codigoCarro)
);

CREATE TABLE Conductores (
	codigoConductor int not null auto_increment,
    nombre varchar(10) not null,
    apellidos varchar(10) not null,
    correo varchar(10) not null,
    usuario varchar(10) not null,
    contrasenia varchar(10) not null,
    codigoCarro int not null,
    espaciosDisponibles int not null,
    ubicacion varchar(50) not null,
    destino varchar(50) not null,
    
    primary key PK_codigoConductor (codigoConductor),
    constraint FK_Carros_Conductores foreign key (codigoCarro)
		references Carros (codigoCarro) ON DELETE CASCADE
);


CREATE TABLE Pasajeros (
	codigoPasajero int not null auto_increment,
    nombre varchar(10) not null,
    apellidos varchar(10) not null,
    correo varchar(15) not null,
	codigoConductor int not null,
    deuda decimal(10,2) not null,
    usuario varchar(10) not null,
    contrasenia varchar(10) not null,
    ubicacion varchar(50) not null,
    destino varchar(50) not null,
	
    primary key PK_codigoPasajero (codigoPasajero),
    constraint FK_Conductores_Pasajeros foreign key (codigoConductor)
		references Conductores (codigoConductor) ON DELETE CASCADE
);


-- Procedimientos tabla Carros
Delimiter $$
CREATE PROCEDURE sp_AgregarCarro(IN marca varchar(10), IN modelo varchar(10), IN cantAsientos int)
BEGIN
	INSERT INTO Carros (marca, modelo, cantAsientos)
		VALUES(marca, modelo, cantAsientos);
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ActualizarCarro(IN codigo int, IN marca varchar(10), IN modelo varchar(10), IN cantAsientos int)
BEGIN
	UPDATE Carros SET marca=marca, modelo=modelo, cantAsientos=cantAsientos WHERE codigoCarro=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_EliminarCarro(IN codigo int)
BEGIN
	DELETE FROM Carros WHERE codigoCarro=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ListarCarros()
BEGIN
	SELECT
		Carros.codigoCarro, 
        Carros.marca, 
        Carros.modelo, 
        Carros.cantAsientos
        FROM Carros;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_BuscarCarro(IN codigo int)
BEGIN
	SELECT
		Carros.codigoCarro, 
        Carros.marca, 
        Carros.modelo, 
        Carros.cantAsientos
        FROM Carros WHERE codigoCarro=codigo;
END$$
Delimiter ;

-- Procedimientos tabla Conductores
Delimiter $$
CREATE PROCEDURE sp_AgregarConductor(IN nombre varchar(10), IN apellidos varchar(10), IN correo varchar(10), IN usuario varchar(10), IN contrasenia varchar(10),
									IN codigoCarro int, IN espaciosDisponibles int, IN ubicacion varchar(50), IN destino varchar(50))
BEGIN
	INSERT INTO Conductores (nombre, apellidos, correo, usuario, contrasenia, codigoCarro, espaciosDisponibles, ubicacion, destino)
		VALUES(nombre, apellidos, correo, usuario, contrasenia, codigoCarro, espaciosDisponibles, ubicacion, destino);
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ActualizarConductor(IN codigo int, IN nombre varchar(10), IN apellidos varchar(10), IN correo varchar(10), IN usuario varchar(10), IN contrasenia varchar(10),
									IN codigoCarro int, IN espaciosDisponibles int, IN ubicacion varchar(50), IN destino varchar(50))
BEGIN
	UPDATE Conductores SET nombre=nombre, apellidos=apellidos, correo=correo, usuario=usuario, contrasenia=contrasenia, codigoCarro=codigoCarro, espacioDisponibles=espaciosDisponibles,
					ubicacion=ubicacion, destino=destino WHERE codigoConductor=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_EliminarConductor(IN codigo int)
BEGIN
	DELETE FROM Conductores WHERE codigoConductor=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ListarConductores()
BEGIN
	SELECT
		Conductores.codigoConductor, 
        Conductores.nombre, 
        Conductores.apellidos, 
        Conductores.correo,
        Conductores.usuario,
        Conductores.contrasenia,
        Conductores.codigoCarro,
        Conductores.espaciosDisponibles,
        Conductores.ubicacion,
        Conductores.destino
        FROM Conductores;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_BuscarConductor(IN codigo int)
BEGIN
	SELECT
		Conductores.codigoConductor, 
        Conductores.nombre, 
        Conductores.apellidos, 
        Conductores.correo,
        Conductores.usuario,
        Conductores.contrasenia,
        Conductores.codigoCarro,
        Conductores.espaciosDisponibles,
        Conductores.ubicacion,
        Conductores.destino
        FROM Conductores WHERE codigoConductor=codigo;
END$$
Delimiter ;


-- Procedimientos tabla Pasajeros
Delimiter $$
CREATE PROCEDURE sp_AgregarPasajero(IN nombre varchar(10), IN apellidos varchar(10), IN correo varchar(10), IN codigoConductor int, IN deuda double(10,2), IN usuario varchar(10), IN contrasenia varchar(10),
									IN ubicacion varchar(50), IN destino varchar(50))
BEGIN
	INSERT INTO Pasajeros (nombre, apellidos, correo, codigoConductor, deuda, usuario, contrasenia, ubicacion, destino)
		VALUES(nombre, apellidos, correo, codigoConductor, deuda, usuario, contrasenia, ubicacion, destino);
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ActualizarPasajero(IN nombre varchar(10), IN apellidos varchar(10), IN correo varchar(10), IN codigoConductor int, IN deuda double(10,2), IN usuario varchar(10), IN contrasenia varchar(10),
									IN ubicacion varchar(50), IN destino varchar(50))
BEGIN
	UPDATE Pasajeros SET nombre=nombre, apellidos=apellidos, correo=correo, codigoConductor=codigoConductor, deuda=deuda, 
		usuario=usuario, contrasenia=contrasenia, ubicacion=ubicacion, destino=destino WHERE codigoPasajero=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_EliminarPasajero(IN codigo int)
BEGIN
	DELETE FROM Pasajeros WHERE codigoPasajero=codigo;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_ListarPasajeros()
BEGIN
	SELECT
		Pasajeros.codigoPasajero, 
        Pasajeros.nombre, 
        Pasajeros.apellidos, 
        Pasajeros.codigoConductor,
        Pasajeros.deuda,
        Pasajeros.usuario,
        Pasajeros.contrasenia,
        Pasajeros.ubicacion,
        Pasajeros.destino
        FROM Pasajeros;
END$$
Delimiter ;

Delimiter $$
CREATE PROCEDURE sp_BuscarPasajero(IN codigo int)
BEGIN
	SELECT
		Pasajeros.codigoPasajero, 
        Pasajeros.nombre, 
        Pasajeros.apellidos, 
        Pasajeros.codigoConductor,
        Pasajeros.deuda,
        Pasajeros.usuario,
        Pasajeros.contrasenia,
        Pasajeros.ubicacion,
        Pasajeros.destino
        FROM Pasajeros WHERE codigoPasajero=codigo;
END$$
Delimiter ;

-- Procedimiento Login
Delimiter $$
CREATE PROCEDURE sp_BusquedaLogin(IN usuario varchar(10), IN contrasenia varchar(10))
BEGIN
	SELECT
		Pasajeros.codigoPasajero
        FROM Pasajeros WHERE usuario=usuario AND contrasenia=contrasenia;
END$$
Delimiter ;


