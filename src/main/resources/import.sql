-- =================================================================================
-- 1. ADMINISTRADOR
-- =================================================================================
-- Usuario Admin (ID 1)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (1, 'Administrador', 'Sistema', 'admin', 'admin@sacristan.edu', '{noop}admin123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');

-- =================================================================================
-- 2. PROFESORES
-- =================================================================================

-- Profesor 1: Laura García (ID 2)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (2, 'Laura', 'García', 'lgarcia', 'laura.garcia@sacristan.edu', '{noop}pass1234', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (2, 'TEACHER');
INSERT INTO teachers (user_id) VALUES (2);

-- Profesor 2: Carlos Ruiz (ID 3)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (3, 'Carlos', 'Ruiz', 'cruiz', 'carlos.ruiz@sacristan.edu', '{noop}pass1234', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (3, 'TEACHER');
INSERT INTO teachers (user_id) VALUES (3);

-- Profesor 3: Elena Martín (ID 4)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (4, 'Elena', 'Martín', 'emartin', 'elena.martin@sacristan.edu', '{noop}pass1234', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (4, 'TEACHER');
INSERT INTO teachers (user_id) VALUES (4);

-- =================================================================================
-- 3. ALUMNOS
-- =================================================================================

-- Ejemplo Alumno 1 (ID 10)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (10, 'Sofía', 'López', 'slopez', 'sofia.lopez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (10, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (10, 2);

-- Alumno 2 (ID 11)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (11, 'Mateo', 'Fernández', 'mfernandez', 'mateo.fernandez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (11, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (11, 2);

-- Alumno 3 (ID 12)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (12, 'Valentina', 'Gómez', 'vgomez', 'valentina.gomez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (12, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (12, 2);

-- Alumno 4 (ID 13)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (13, 'Alejandro', 'Díaz', 'adiaz', 'alejandro.diaz@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (13, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (13, 2);

-- Alumno 5 (ID 20)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (20, 'Lucía', 'Pérez', 'lperez', 'lucia.perez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (20, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (20, 3);

-- Alumno 6 (ID 21)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (21, 'Daniel', 'Sánchez', 'dsanchez', 'daniel.sanchez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (21, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (21, 3);

-- Alumno 7 (ID 22)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (22, 'Martina', 'Romero', 'mromero', 'martina.romero@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (22, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (22, 3);

-- Alumno 8 (ID 30)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (30, 'Hugo', 'Alvarez', 'halvarez', 'hugo.alvarez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (30, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (30, 4);

-- Alumno 9 (ID 31)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (31, 'Julia', 'Torres', 'jtorres', 'julia.torres@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (31, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (31, 4);

-- Alumno 10 (ID 32)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (32, 'Pablo', 'Ruiz', 'pruiz_student', 'pablo.ruiz@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (32, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (32, 4);

-- Alumno 11 (ID 33)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (33, 'Emma', 'Vázquez', 'evazquez', 'emma.vazquez@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (33, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (33, 4);

-- Alumno 12 (ID 34)
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (34, 'Álvaro', 'Moreno', 'amoreno', 'alvaro.moreno@student.sacristan.edu', '{noop}student123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (34, 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (34, 4);

ALTER TABLE users ALTER COLUMN id RESTART WITH 100;