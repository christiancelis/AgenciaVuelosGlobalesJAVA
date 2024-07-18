use railway;

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
INSERT INTO Fabricante (id, nombre) VALUES (1, 'Boeing');
INSERT INTO Fabricante (id, nombre) VALUES (2, 'Airbus');
INSERT INTO Fabricante (id, nombre) VALUES (3, 'Embraer');
INSERT INTO Fabricante (id, nombre) VALUES (4, 'Bombardier');
INSERT INTO Fabricante (id, nombre) VALUES (5, 'Cessna');


-- Inserciones para la tabla Modelo
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (1, '747', 1); -- Boeing 747
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (2, 'A380', 2); -- Airbus A380
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (3, 'E190', 3); -- Embraer E190
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (4, 'CRJ900', 4); -- Bombardier CRJ900
INSERT INTO Modelo (id, nombre, Fabricante_id) VALUES (5, 'Citation X', 5); -- Cessna Citation X

INSERT INTO Pais (id, nombre) VALUES
(1, 'Colombia'),
(2, 'Venezuela'),
(3, 'Mexico'),
(4, 'Peru'),
(5, 'Paraguay'),
(6, 'Uruguay'),
(7, 'Bolivia'),
(8, 'Costa Rica'),
(9, 'Argentina'),
(10, 'Trinidad y Tobago'),
(11, 'Salvador'),
(12, 'España'),
(13, 'Estados Unidos'),
(14, 'Ecuador'),
(15, 'Puerto Rico'),
(16, 'Alemania'),
(17, 'Holanda'),
(18, 'Japon');


INSERT INTO Ciudad (id, nombre, Pais_id) VALUES
(1, 'Bogotá', 1),
(2, 'Medellín', 1),
(3, 'Caracas', 2),
(4, 'Maracaibo', 2),
(5, 'Ciudad de México', 3),
(6, 'Guadalajara', 3),
(7, 'Lima', 4),
(8, 'Cusco', 4),
(9, 'Asunción', 5),
(10, 'Ciudad del Este', 5),
(11, 'Montevideo', 6),
(12, 'Salto', 6),
(13, 'La Paz', 7),
(14, 'Santa Cruz', 7),
(15, 'San José', 8),
(16, 'Limón', 8),
(17, 'Buenos Aires', 9),
(18, 'Córdoba', 9),
(19, 'Puerto España', 10),
(20, 'San Fernando', 10),
(21, 'San Salvador', 11),
(22, 'Santa Ana', 11),
(23, 'Madrid', 12),
(24, 'Barcelona', 12),
(25, 'Nueva York', 13),
(26, 'Los Ángeles', 13),
(27, 'Quito', 14),
(28, 'Guayaquil', 14),
(29, 'San Juan', 15),
(30, 'Ponce', 15),
(31, 'Berlín', 16),
(32, 'Múnich', 16),
(33, 'Ámsterdam', 17),
(34, 'Rotterdam', 17),
(35, 'Tokio', 18),
(36, 'Osaka', 18);



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
(45, "Modificar Reserva de Vuelo");

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
(1, 30, "tarifa"), -- Registrar Tarifa de Vuelo
(1, 31, "tarifa"), -- Actualizar Información de Tarifa de Vuelo
(1, 32, "tarifa"), -- Eliminar Tarifa de Vuelo
(1, 33, "tarifa"), -- Consultar Tarifa de Vuelo
(1, 34, "documentacion"), -- Registrar Tipo de Documento
(1, 35, "documentacion"), -- Actualizar Tipo de Documento
(1, 36, "documentacion"), -- Eliminar Tipo de Documento
(1, 37, "documentacion"), -- Consultar Tipo de Documento

-- Relacionando permisos para el rol VENTAS
(2, 5, "cliente"),  -- Consultar Información de Cliente
(2, 7, "cliente"),  -- Registrar Cliente
(2, 13, "cliente"), -- Actualizar Información de Cliente
(2, 22, "cliente"), -- Actualizar Información de Cliente
(2, 3, "reserva"),  -- Crear Reserva de Vuelo
(2, 6, "reserva"),  -- Consultar Reserva de Vuelo
(2, 14, "reserva"), -- Eliminar Reserva de Vuelo
(2, 23, "viaje"), -- Consultar Información de Vuelo

-- Relacionando permisos para el rol TECNICO
(3, 8, "avion"),  -- Consultar Información de Avión
(3, 12, "avion"), -- Consultar Historial de Revisiones de Avión
(3, 15, "avion"), -- Actualizar Información de Avión
(3, 4, "mantenimiento"),  -- Registrar Revisión de Mantenimiento
(3, 24, "mantenimiento"), -- Actualizar Información de Revisión
(3, 25, "mantenimiento"), -- Eliminar Revisión de Mantenimiento

-- Relacionando permisos para el rol CLIENTE
(4, 38, "viaje"), -- Buscar Vuelos
(4, 39, "viaje"), -- Seleccionar Vuelo
(4, 40, "viaje"), -- Añadir Pasajeros
(4, 41, "viaje"), -- Seleccionar Asientos
(4, 42, "viaje"), -- Realizar Pago
(4, 43, "reserva"), -- Consultar Reserva de Vuelo
(4, 44, "reserva"), -- Cancelar Reserva de Vuelo
(4, 45, "reserva"); -- Modificar Reserva de Vuelo


ALTER TABLE Aeropuerto MODIFY COLUMN nombre VARCHAR(100);


INSERT INTO Aeropuerto (id, nombre, Ciudad_id) VALUES
    (1, 'El Dorado International', 1),
    (2, 'José María Córdova International', 2),
    (3, 'Simón Bolívar International Airport', 3),
    (4, 'La Chinita International Airport', 4),
    (5, 'Benito Juárez International Airport', 5),
    (6, 'Don Miguel Hidalgo y Costilla International Airport', 6),
    (7, 'Jorge Chávez International Airport', 7),
    (8, 'Alejandro Velasco Astete International Airport', 8),
    (9, 'Silvio Pettirossi International Airport', 9),
    (10, 'Guaraní International Airport', 10),
    (11, 'Carrasco International Airport', 11),
    (12, 'Nueva Hespérides International Airport', 12),
    (13, 'El Alto International Airport', 13),
    (14, 'Viru Viru International Airport', 14),
    (15, 'Juan Santamaría International Airport', 15),
    (16, 'Limón International Airport', 16),
    (17, 'Ministro Pistarini International Airport', 17),
    (18, 'Ingeniero Aeronáutico Ambrosio L.V. Taravella International Airport', 18),
    (19, 'Piarco International Airport', 19),
    (20, 'San Fernando Airport', 20),
    (21, 'Monsenor Oscar Arnulfo Romero International Airport', 21),
    (22, 'Ilopango Airport', 22),
    (23, 'Adolfo Suárez Madrid–Barajas Airport', 23),
    (24, 'Barcelona-El Prat Airport', 24),
    (25, 'John F. Kennedy International Airport', 25),
    (26, 'Los Angeles International Airport', 26),
    (27, 'Mariscal Sucre International Airport', 27),
    (28, 'José Joaquín de Olmedo International Airport', 28),
    (29, 'Luis Muñoz Marín International Airport', 29),
    (30, 'Mercedita Airport', 30),
    (31, 'Berlin Brandenburg Airport', 31),
    (32, 'Munich Airport', 32),
    (33, 'Amsterdam Airport Schiphol', 33),
    (34, 'Rotterdam The Hague Airport', 34),
    (35, 'Narita International Airport', 35),
    (36, 'Kansai International Airport', 36);


INSERT INTO Viaje (id, aeropuertoOrigen, aeropuertoDestino, fechaViaje, precio) VALUES
(1, 1, 5, '2024-07-20', 200),
(2, 3, 2, '2024-07-21', 250),
(3, 2, 6, '2024-07-22', 180),
(4, 2, 4, '2024-07-23', 300),
(5, 7, 9, '2024-08-01', 350),
(6, 11, 13, '2024-08-02', 400),
(7, 15, 17, '2024-08-03', 450),
(8, 19, 21, '2024-08-04', 500),
(9, 23, 25, '2024-08-05', 550),
(10, 27, 29, '2024-08-06', 600),
(11, 31, 33, '2024-08-07', 650),
(12, 35, 1, '2024-08-08', 700),
(13, 4, 6, '2024-08-09', 220),
(14, 8, 10, '2024-08-10', 240),
(15, 12, 14, '2024-08-11', 260),
(16, 16, 18, '2024-08-12', 280),
(17, 20, 22, '2024-08-13', 300),
(18, 24, 26, '2024-08-14', 320),
(19, 28, 30, '2024-08-15', 340),
(20, 32, 34, '2024-08-16', 360);


SELECT rp.idRol, rp.idPermiso, p.nombre AS nombrePermiso, rp.validacion, u.id as idUsuario
                     FROM RolPermiso rp 
                     JOIN Permiso as p ON p.id = rp.idPermiso 
                     JOIN Usuario as u on u.rolId = rp.idRol;


