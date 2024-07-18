drop database railway;
create DATABASE IF not EXISTS railway;
USE railway;

-- Creación de la tabla Fabricante
CREATE TABLE Fabricante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40)
);

-- Creación de la tabla Modelo
CREATE TABLE Modelo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    Fabricante_id INT,
    FOREIGN KEY (Fabricante_id) REFERENCES Fabricante(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Estado
CREATE TABLE Estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30)
);

-- Creación de la tabla Avion
CREATE TABLE Avion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(45),
    capacidad INT,
    fechaFabricacion DATE,
    Modelo_id INT,
    Estado_id INT,
    FOREIGN KEY (Modelo_id) REFERENCES Modelo(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Estado_id) REFERENCES Estado(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Rol
CREATE TABLE Rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Creación de la tabla Pais
CREATE TABLE Pais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Creación de la tabla Aerolinea
CREATE TABLE Aerolinea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Creación de la tabla Ciudad
CREATE TABLE Ciudad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    Pais_id INT,
    FOREIGN KEY (Pais_id) REFERENCES Pais(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Aeropuerto
CREATE TABLE Aeropuerto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    Ciudad_id INT,
    FOREIGN KEY (Ciudad_id) REFERENCES Ciudad(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Salida
CREATE TABLE Salida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroSalida VARCHAR(5),
    Aeropuerto_id INT,
    FOREIGN KEY (Aeropuerto_id) REFERENCES Aeropuerto(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Empleado
CREATE TABLE Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    fechaIngreso INT,
    Rol_id INT,
    Aerolinea_id INT,
    FOREIGN KEY (Rol_id) REFERENCES Rol(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Aerolinea_id) REFERENCES Aerolinea(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Revision
CREATE TABLE Revision (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaRevision VARCHAR(45),
    Avion_id INT,
    FOREIGN KEY (Avion_id) REFERENCES Avion(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Revision_Empleado
CREATE TABLE Revision_Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Revision_id INT,
    Empleado_id INT,
    descripcion VARCHAR(45),
    FOREIGN KEY (Empleado_id) REFERENCES Empleado(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Revision_id) REFERENCES Revision(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Viaje
CREATE TABLE Viaje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aeropuertoOrigen INT,
    aeropuertoDestino INT,
    fechaViaje DATETIME,
    precio DECIMAL NOT NULL,
    FOREIGN KEY (aeropuertoOrigen) REFERENCES Aeropuerto(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (aeropuertoDestino) REFERENCES Aeropuerto(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Escala
CREATE TABLE Escala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aeropuertoDestino INT,
    aeropuertoOrigen INT,
    Avion_id INT,
    Viaje_id INT,
    FOREIGN KEY (Avion_id) REFERENCES Avion(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Viaje_id) REFERENCES Viaje(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (aeropuertoOrigen) REFERENCES Aeropuerto(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (aeropuertoDestino) REFERENCES Aeropuerto(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Tripulante
CREATE TABLE Tripulante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Escala_id INT,
    Empleado_id INT,
    FOREIGN KEY (Escala_id) REFERENCES Escala(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Empleado_id) REFERENCES Empleado(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Documento
CREATE TABLE Documento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

-- Creación de la tabla Cliente
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    edad VARCHAR(45),
    Documento_id INT,
    FOREIGN KEY (Documento_id) REFERENCES Documento(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Tarifa
CREATE TABLE Tarifa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(45),
    detalle VARCHAR(45),
    valor VARCHAR(45)
);

-- Creación de la tabla detalleReserva
CREATE TABLE detalleReserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Tarifa_id INT,
    Cliente_id INT,
    FOREIGN KEY (Tarifa_id) REFERENCES Tarifa(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Cliente_id) REFERENCES Cliente(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla ReservaViaje
CREATE TABLE ReservaViaje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    Viaje_id INT,
    detalleReserva_id INT,
    FOREIGN KEY (Viaje_id) REFERENCES Viaje(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (detalleReserva_id) REFERENCES detalleReserva(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla Clase
CREATE TABLE Clase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipoClase VARCHAR(10)
);

-- Creación de la tabla Asiento
CREATE TABLE Asiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAvion INT,
    numeroAsiento VARCHAR(5),
    fila INT,
    columna INT,
    idClase INT,
    FOREIGN KEY (idAvion) REFERENCES Avion(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (idClase) REFERENCES Clase(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla ReservaAsiento
CREATE TABLE ReservaAsiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAsiento INT,
    idReserva INT,
    FOREIGN KEY (idAsiento) REFERENCES Asiento(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (idReserva) REFERENCES ReservaViaje(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Creación de la tabla AutenticacionRol
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE,
    contraseña VARCHAR(10),
    rolId INT,
    FOREIGN KEY (rolId) REFERENCES Rol(id) ON DELETE SET NULL ON UPDATE CASCADE
);

create table Permiso(
id int primary key auto_increment,
nombre varchar(100)
);

create table RolPermiso(
idRol int,
idPermiso int,
validacion varchar(50),
primary key(idRol,idPermiso),
foreign key(idRol)references Rol(id),
foreign key(idPermiso)references Permiso(id)
);

create view VistaPaisAero
as select p.id as idPais,p.nombre as pais,c.id as idCiudad,c.nombre as ciudad from Pais as p
join Ciudad as c on c.Pais_id = p.id;



show tables;

