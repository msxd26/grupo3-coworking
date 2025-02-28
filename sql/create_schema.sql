CREATE TABLE usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL, -- Almacenar contraseñas cifradas
                          fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Salas
CREATE TABLE salas (
                       id_sala SERIAL PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       capacidad INT NOT NULL,
                       descripcion TEXT,
                       estado VARCHAR(20) CHECK (estado IN ('disponible', 'ocupada', 'mantenimiento')) DEFAULT 'disponible'
);

-- Tabla Reservas
CREATE TABLE reservas (
                          id_reserva SERIAL PRIMARY KEY,
                          id_usuario INT NOT NULL, -- Usuario que realiza la reserva
                          id_sala INT NOT NULL, -- Sala reservada
                          fecha_inicio TIMESTAMP NOT NULL,
                          fecha_fin TIMESTAMP NOT NULL,
                          estado VARCHAR(20) CHECK (estado IN ('activa', 'cancelada')) DEFAULT 'activa',
                          FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
                          FOREIGN KEY (id_sala) REFERENCES salas(id_sala) ON DELETE CASCADE
);

-- Tabla Participantes
CREATE TABLE participantes (
                               id_participante SERIAL PRIMARY KEY,
                               id_reserva INT NOT NULL,
                               id_usuario INT NOT NULL,
                               FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva) ON DELETE CASCADE,
                               FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla Roles
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabla Usuario_Roles
CREATE TABLE usuario_roles (
                               usuario_id INT NOT NULL,
                               rol_id INT NOT NULL,
                               PRIMARY KEY (usuario_id, rol_id),
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
                               FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE
);