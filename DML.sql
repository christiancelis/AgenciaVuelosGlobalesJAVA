

-- Estados
INSERT INTO Estado(id, nombre) VALUES (1, "En espera");
INSERT INTO Estado(id, nombre) VALUES (2, "Despegando");
INSERT INTO Estado(id, nombre) VALUES (3, "En vuelo");
INSERT INTO Estado(id, nombre) VALUES (4, "Aterrizando");
INSERT INTO Estado(id, nombre) VALUES (5, "En mantenimiento");
INSERT INTO Estado(id, nombre) VALUES (6, "Listo para vuelo");
INSERT INTO Estado(id, nombre) VALUES (7, "Retrasado");
INSERT INTO Estado(id, nombre) VALUES (8, "Cancelado");
INSERT INTO Estado(id, nombre) VALUES (9, "Desviando");



-- Inserciones para la tabla Fabricante
INSERT INTO Fabricante (nombre) VALUES ('Boeing');
INSERT INTO Fabricante (nombre) VALUES ( 'Airbus');
INSERT INTO Fabricante ( nombre) VALUES ( 'Embraer');
INSERT INTO Fabricante ( nombre) VALUES ( 'Bombardier');
INSERT INTO Fabricante ( nombre) VALUES ( 'Cessna');


-- Inserciones para la tabla Modelo
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (1, '747', 1); -- Boeing 747
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (2, 'A380', 2); -- Airbus A380
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (3, 'E190', 3); -- Embraer E190
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (4, 'CRJ900', 4); -- Bombardier CRJ900
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (5, 'Citation X', 5); -- Cessna Citation X



insert into Rol values
(1,"ADMINISTRADOR"),
(2,"VENTAS"),
(3,"TECNICO"),
(4,"CLIENTE");

INSERT INTO Permiso (id, nombre) VALUES
(1, "Registrar Avión"),
(2, "Asignar Tripulación a Trayecto"),
(3, "Crear Reserva de Vuelo"),
(4, "Registrar Revisión de Mantenimiento"),
(5, "Consultar Información de Cliente"),
(6, "Consultar Reserva de Vuelo"),
(7, "Registrar Cliente"),
(8, "Consultar Información de Avión"),
(9, "Consultar Información de Trayecto"),
(10, "Registrar Aeropuerto"),
(11, "Consultar Información de Aeropuerto"),
(12, "Consultar Historial de Revisiones de Avión"),
(13, "Actualizar Información de Cliente"),
(14, "Eliminar Reserva de Vuelo"),
(15, "Actualizar Información de Avión"),
(16, "Eliminar Avión"),
(17, "Asignar Aeronave a Trayecto"),
(18, "Actualizar Información de Trayecto"),
(19, "Eliminar Trayecto"),
(20, "Actualizar Información de Aeropuerto"),
(21, "Eliminar Aeropuerto"),
(22, "Actualizar Información de Cliente"),
(23, "Consultar Información de Vuelo"),
(24, "Actualizar Información de Revisión"),
(25, "Eliminar Revisión de Mantenimiento"),
(26, "Consultar Asignación de Tripulación"),
(27, "Consultar Escalas de un Trayecto"),
(28, "Actualizar Información de Escala"),
(29, "Eliminar Escala"),
(30, "Registrar Tarifa de Vuelo"),
(31, "Actualizar Información de Tarifa de Vuelo"),
(32, "Eliminar Tarifa de Vuelo"),
(33, "Consultar Tarifa de Vuelo"),
(34, "Registrar Tipo de Documento"),
(35, "Actualizar Tipo de Documento"),
(36, "Eliminar Tipo de Documento"),
(37, "Consultar Tipo de Documento"),
(38, "Buscar Vuelos"),
(39, "Seleccionar Vuelo"),
(40, "Añadir Pasajeros"),
(41, "Seleccionar Asientos"),
(42, "Realizar Pago"),
(43, "Consultar Reserva de Vuelo"),
(44, "Cancelar Reserva de Vuelo"),
(45, "Modificar Reserva de Vuelo"),
(46, "Registrar Vuelo"),
(47, "Registrar Trayecto"),
(48, "Validar Vuelo"),
(49, "Control de Vuelo");

-- Relacionando permisos para el rol ADMINISTRADOR
INSERT INTO RolPermiso (idRol, idPermiso, validacion) VALUES
(1, 1, "avion"),  -- Registrar Avión
(1, 8, "avion"),  -- Consultar Información de Avión
(1, 12, "avion"), -- Consultar Historial de Revisiones de Avión
(1, 15, "avion"), -- Actualizar Información de Avión
(1, 16, "avion"), -- Eliminar Avión
(1, 10, "aeropuerto"), -- Registrar Aeropuerto
(1, 11, "aeropuerto"), -- Consultar Información de Aeropuerto
(1, 20, "aeropuerto"), -- Actualizar Información de Aeropuerto
(1, 21, "aeropuerto"), -- Eliminar Aeropuerto
(1, 2, "tripulacion"),  -- Asignar Tripulación a Trayecto
(1, 26, "tripulacion"), -- Consultar Asignación de Tripulación
(1, 9, "viaje"),  -- Consultar Información de Trayecto
(1, 17, "viaje"), -- Asignar Aeronave a Trayecto
(1, 18, "viaje"), -- Actualizar Información de Trayecto
(1, 19, "viaje"), -- Eliminar Trayecto
(1, 27, "viaje"), -- Consultar Escalas de un Trayecto
(1, 28, "viaje"), -- Actualizar Información de Escala
(1, 29, "viaje"), -- Eliminar Escala
(1,46,"viaje"),-- Registrar vuelo
(1,47,"viaje"),-- registrar trayecto
(1,48,"viaje"),
(1,49,"viaje"),
(1, 30, "tarifa"), -- Registrar Tarifa de Vuelo
(1, 31, "tarifa"), -- Actualizar Información de Tarifa de Vuelo
(1, 32, "tarifa"), -- Eliminar Tarifa de Vuelo
(1, 33, "tarifa"), -- Consultar Tarifa de Vuelo
(1, 34, "documentacion"), -- Registrar Tipo de Documento
(1, 35, "documentacion"), -- Actualizar Tipo de Documento
(1, 36, "documentacion"), -- Eliminar Tipo de Documento
(1, 37, "documentacion"), -- Consultar Tipo de Documento
(2, 5, "cliente"),  -- Consultar Información de Cliente
(2, 7, "cliente"),  -- Registrar Cliente
(2, 13, "cliente"), -- Actualizar Información de Cliente
(2, 22, "cliente"), -- Actualizar Información de Cliente
(2, 3, "reserva"),  -- Crear Reserva de Vuelo
(2, 6, "reserva"),  -- Consultar Reserva de Vuelo
(2, 14, "reserva"), -- Eliminar Reserva de Vuelo
(2, 23, "viaje"), -- Consultar Información de Vuelo
(3, 8, "avion"),  -- Consultar Información de Avión
(3, 12, "avion"), -- Consultar Historial de Revisiones de Avión
(3, 15, "avion"), -- Actualizar Información de Avión
(3, 4, "mantenimiento"),  -- Registrar Revisión de Mantenimiento
(3, 24, "mantenimiento"), -- Actualizar Información de Revisión
(3, 25, "mantenimiento"), -- Eliminar Revisión de Mantenimiento
(4, 38, "viaje"), -- Buscar Vuelos
(4, 39, "viaje"), -- Seleccionar Vuelo
(4, 40, "viaje"), -- Añadir Pasajeros
(4, 41, "viaje"), -- Seleccionar Asientos
(4, 42, "viaje"), -- Realizar Pago
(4, 43, "reserva"), -- Consultar Reserva de Vuelo
(4, 44, "reserva"), -- Cancelar Reserva de Vuelo
(4, 45, "reserva"); 


 INSERT INTO Aerolinea (nombre)
VALUES 	('Copa Airlines'),
		('American Airlines'),
       ('Delta Air Lines'),
       ('United Airlines'),
       ('British Airways'),
       ('Lufthansa'),
       ('Air France'),
       ('Emirates'),
       ('Qatar Airways'),
       ('Singapore Airlines'),
       ('Cathay Pacific');
       

INSERT INTO Empleado (nombre, fechaIngreso, Aerolinea_id)
VALUES 
    ('Juan Pérez', '2023-01-15', 1),   -- Copa Airlines
    ('María Rodríguez', '2022-11-20', 1),
    ('Pedro Gómez', '2023-03-05', 1),
    ('Ana Martínez', '2022-09-10', 1),
    ('Luis Sánchez', '2023-02-28', 1),

    ('Emily Johnson', '2023-04-18', 2),   -- American Airlines
    ('Michael Brown', '2022-12-30', 2),
    ('Jessica Davis', '2023-01-25', 2),
    ('Daniel Miller', '2023-03-15', 2),
    ('Sarah Wilson', '2022-08-05', 2),

    ('Thomas Clark', '2023-02-10', 3),  -- Delta Air Lines
    ('Olivia Lee', '2022-10-12', 3),
    ('David Moore', '2023-04-02', 3),
    ('Sophia Taylor', '2023-01-08', 3),
    ('James Anderson', '2022-11-25', 3),

    ('Emma White', '2022-12-15', 4),  -- United Airlines
    ('Alexander Garcia', '2023-03-20', 4),
    ('Isabella Martinez', '2023-05-10', 4),
    ('William Hernandez', '2022-09-05', 4),
    ('Oliver Lopez', '2023-01-30', 4),

    ('Charlotte Wilson', '2023-04-20', 5),  -- British Airways
    ('Mason Moore', '2022-11-15', 5),
    ('Amelia King', '2023-02-08', 5),
    ('Ethan Adams', '2022-10-30', 5),
    ('Ava Turner', '2023-03-18', 5),

    ('Sophie Fischer', '2022-08-20', 6),  -- Lufthansa
    ('Maximilian Schmitt', '2023-01-12', 6),
    ('Lara Weber', '2023-03-28', 6),
    ('Leon Müller', '2022-12-05', 6),
    ('Finn Fischer', '2023-02-15', 6),

    ('Juliette Martin', '2022-11-02', 7),  -- Air France
    ('Gabriel Lefevre', '2023-04-05', 7),
    ('Lucas Blanc', '2023-01-25', 7),
    ('Manon Dubois', '2022-09-18', 7),
    ('Chloé Moreau', '2023-03-10', 7),

    ('Zara Khan', '2023-05-15', 8),  -- Emirates
    ('Aryan Patel', '2022-12-10', 8),
    ('Ayesha Ali', '2022-08-28', 8),
    ('Kian Sheikh', '2023-02-22', 8),
    ('Laila Rahman', '2023-01-05', 8),

    ('Adam Wong', '2022-10-20', 9),  -- Qatar Airways
    ('Sara Ahmed', '2023-04-18', 9),
    ('Rayan Khan', '2023-01-15', 9),
    ('Yara Al-Abadi', '2022-09-30', 9),
    ('Omar Nassar', '2023-03-25', 9),

    ('Ella Tan', '2023-02-20', 10),  -- Singapore Airlines
    ('Ryan Lim', '2022-12-05', 10),
    ('Sophia Ng', '2023-01-28', 10),
    ('Daniel Chan', '2022-11-10', 10),
    ('Liam Tan', '2023-03-15', 10),

    ('Charlotte Wong', '2022-10-10', 11),  -- Cathay Pacific
    ('Benjamin Cheung', '2023-04-02', 11),
    ('Mia Chan', '2023-01-20', 11),
    ('Lucas Ho', '2022-09-12', 11),
    ('Grace Yip', '2023-02-28', 11);


         
delimiter $$

create procedure CreateRevision(
    in fechaR VARCHAR(45),
    in idAvion int ,
    in EmpleadoId int ,
    in descri VARCHAR(45)  
)
begin
	declare id int ;
	insert into Revision (fechaRevision, Avion_id) VALUES(fechaR,idAvion);
	set id = LAST_INSERT_ID();
    insert into Revision_Empleado(Revision_id,Empleado_id,descripcion) VALUES(id,EmpleadoId,descri);
end$$
delimiter ;
/*
CALL CreateRevision(
    '2024-07-23',   -- fechaR
    2,            -- idAvion
    2,             -- EmpleadoId
    'Revisión de motor'  -- descri
); 
*/

DELIMITER $$

CREATE PROCEDURE ActualizarRevision(
    IN idR INT,
    IN fechaR VARCHAR(45),
    IN idAvion INT,
    IN EmpleadoId INT,
    IN descri VARCHAR(45)  
)
BEGIN
    -- Actualizar la tabla Revision
    UPDATE Revision 
    SET fechaRevision = fechaR, Avion_id = idAvion 
    WHERE id = idR;
    -- Actualizar la tabla Revision_Empleado
    UPDATE Revision_Empleado 
    SET Revision_id = idR, Empleado_id = EmpleadoId, descripcion = descri 
    WHERE Revision_id = idR;
END$$

DELIMITER ;

call ActualizarRevision(1, '2024-02-12',2,4,'Revision de Alerones');

-- Inserciones para la tabla Modelo
INSERT INTO Modelo ( nombre, Fabricante_id) VALUES ( '747', 1); -- Boeing 747
INSERT INTO Modelo ( nombre, Fabricante_id) VALUES ( 'A380', 2); -- Airbus A380
INSERT INTO Modelo (nombre, Fabricante_id) VALUES ( 'E190', 3); -- Embraer E190
INSERT INTO Modelo ( nombre, Fabricante_id) VALUES ( 'CRJ900', 4); -- Bombardier CRJ900
INSERT INTO Modelo ( nombre, Fabricante_id) VALUES ( 'Citation X', 5); -- Cessna Citation X


insert into Usuario(id,usuario,contraseña,rolId)values
(1004921441,"christian1",123,1),
(1005323441,"camilo1",123,1),
(2,"christian2",123,2),
(3,"camilo2",123,3),
(4,"camilo3",123,4);

insert into Documento(nombre) values("cedula");

insert into Avion(matricula,capacidad,fechaFabricacion,Modelo_id,Estado_id)
values("ass",69,"2023-06-05",2,4);

INSERT INTO Tarifa (descripcion, detalle, valor) VALUES 
('Tarifa Standard', 'Tarifa básica para vuelos nacionales', 0.1), 
('Tarifa Premium', 'Tarifa para vuelos nacionales', 0.2), 
('Tarifa Internacional', 'Tarifa para vuelos internacionales', 0.3), 
('Tarifa Ejecutiva', 'Tarifa para vuelos de clase ejecutiva', 0.4), 
('Tarifa Primera Clase', 'Tarifa para primera clase internacional', 0.5), 
('Tarifa Reducida', 'Tarifa para estudiantes', 0.05), 
('Tarifa Familia', 'Tarifa para familias con 3+ miembros', 0.1), 
('Tarifa Corporativa', 'Tarifa para empresas con contrato', 0.2), 
('Tarifa Fin de Semana', 'Tarifa para fines de semana', 0.4);

DELIMITER $$

CREATE PROCEDURE InsertVueloTrayecto(
    IN idVuelo INT,
    IN idTrayecto INT
)
BEGIN
    INSERT INTO ViajeEscala (idViaje, idEscala)
    VALUES (idVuelo, idTrayecto);
END$$

DELIMITER ;

select * from Viaje;
insert into Viaje(originAirport,originCity,destinationAirport,destinationCity,precio,fechaViaje,hora,idAvion)
values("Palonegro Airport","El Dorado International Airport","Bucaramanga","Bogotá, Distrito Capital",231898.03,2022-06-06,06:30:00,1);
