-- Insertar datos en la tabla de roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ACOMODADOR');


-- Insertar datos en la tabla de usuarios
INSERT INTO usuarios (username, password) VALUES ('admin', '$2a$10$YXFgohd.a9Ek.IooQJsKIOxWpRjO6GURCrIuFoLquuVwLfBCKu5yO');
-- Contraseña: '12345'



-- Asignar roles a los usuarios
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- Asignar ROLE_ADMIN al usuario admin

--Asignar tipos de carro
INSERT INTO tipo_carro (name) VALUES ('Sedan');
INSERT INTO tipo_carro (name) VALUES ('Chevrolet');
INSERT INTO tipo_carro (name) VALUES ('Kia');
INSERT INTO tipo_carro (name) VALUES ('Tesla');