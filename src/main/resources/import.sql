-- =================================================================================
-- 1. ADMINISTRADOR
-- =================================================================================
-- Usuario Admin (ID 1)
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (1, 'Administrador', 'Sistema', 'admin', 'admin@sacristan.edu', 'admin123', 'ADMIN');


-- =================================================================================
-- 2. PROFESORES (3 Usuarios + 3 Entradas en tabla teachers)
-- =================================================================================

-- Profesor 1: Laura García (Ciencias) - ID 2
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (2, 'Laura', 'García', 'lgarcia', 'laura.garcia@sacristan.edu', 'pass1234', 'TEACHER');

INSERT INTO teachers (user_id) VALUES (2);

-- Profesor 2: Carlos Ruiz (Historia) - ID 3
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (3, 'Carlos', 'Ruiz', 'cruiz', 'carlos.ruiz@sacristan.edu', 'pass1234', 'TEACHER');

INSERT INTO teachers (user_id) VALUES (3);

-- Profesor 3: Elena Martín (Inglés) - ID 4
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (4, 'Elena', 'Martín', 'emartin', 'elena.martin@sacristan.edu', 'pass1234', 'TEACHER');

INSERT INTO teachers (user_id) VALUES (4);


-- =================================================================================
-- 3. ALUMNOS DEL PROFESOR 1 (Laura - ID 2)
-- Asignamos 4 alumnos
-- =================================================================================

-- Alumno 1
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (10, 'Sofía', 'López', 'slopez', 'sofia.lopez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (10, 2);

-- Alumno 2
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (11, 'Mateo', 'Fernández', 'mfernandez', 'mateo.fernandez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (11, 2);

-- Alumno 3
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (12, 'Valentina', 'Gómez', 'vgomez', 'valentina.gomez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (12, 2);

-- Alumno 4
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (13, 'Alejandro', 'Díaz', 'adiaz', 'alejandro.diaz@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (13, 2);


-- =================================================================================
-- 3. ALUMNOS DEL PROFESOR 2 (Carlos - ID 3)
-- Asignamos 3 alumnos
-- =================================================================================

-- Alumno 5
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (20, 'Lucía', 'Pérez', 'lperez', 'lucia.perez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (20, 3);

-- Alumno 6
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (21, 'Daniel', 'Sánchez', 'dsanchez', 'daniel.sanchez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (21, 3);

-- Alumno 7
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (22, 'Martina', 'Romero', 'mromero', 'martina.romero@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (22, 3);


-- =================================================================================
-- 3. ALUMNOS DEL PROFESOR 3 (Elena - ID 4)
-- Asignamos 5 alumnos
-- =================================================================================

-- Alumno 8
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (30, 'Hugo', 'Alvarez', 'halvarez', 'hugo.alvarez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (30, 4);

-- Alumno 9
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (31, 'Julia', 'Torres', 'jtorres', 'julia.torres@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (31, 4);

-- Alumno 10
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (32, 'Pablo', 'Ruiz', 'pruiz_student', 'pablo.ruiz@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (32, 4);

-- Alumno 11
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (33, 'Emma', 'Vázquez', 'evazquez', 'emma.vazquez@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (33, 4);

-- Alumno 12
INSERT INTO users (id, name, last_name, username, email, password, role) VALUES (34, 'Álvaro', 'Moreno', 'amoreno', 'alvaro.moreno@student.sacristan.edu', 'student123', 'STUDENT');
INSERT INTO students (user_id, teacher_id) VALUES (34, 4);