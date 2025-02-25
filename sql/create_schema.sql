-- Tabla Usuarios
CREATE TABLE Usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL, -- Almacenar contraseñas cifradas
                          fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Salas
CREATE TABLE Salas (
                       id_sala SERIAL PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       capacidad INT NOT NULL,
                       descripcion TEXT,
                       estado VARCHAR(20) CHECK (estado IN ('disponible', 'ocupada', 'mantenimiento')) DEFAULT 'disponible'
);

-- Tabla Reservas
CREATE TABLE Reservas (
                          id_reserva SERIAL PRIMARY KEY,
                          id_usuario INT NOT NULL, -- Usuario que realiza la reserva
                          id_sala INT NOT NULL, -- Sala reservada
                          fecha_inicio TIMESTAMP NOT NULL,
                          fecha_fin TIMESTAMP NOT NULL,
                          estado VARCHAR(20) CHECK (estado IN ('activa', 'cancelada')) DEFAULT 'activa',
                          FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
                          FOREIGN KEY (id_sala) REFERENCES Salas(id_sala)
);

-- Tabla Participantes
CREATE TABLE Participantes (
                               id_participante SERIAL PRIMARY KEY,
                               id_reserva INT NOT NULL,
                               id_usuario INT NOT NULL,
                               FOREIGN KEY (id_reserva) REFERENCES Reservas(id_reserva),
                               FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);