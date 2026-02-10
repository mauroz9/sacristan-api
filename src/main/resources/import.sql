-- =================================================================================
-- 1. ADMINISTRADOR
-- =================================================================================
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES (1, 'Administrador', 'Sistema', 'admin', 'admin@sacristan.edu', '{noop}admin123', true, true, true, true);
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');

-- =================================================================================
-- 2. PROFESORES
-- =================================================================================
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
                                                                                                                                                  (2, 'Laura', 'García', 'lgarcia', 'laura.garcia@sacristan.edu', '{noop}pass1234', true, true, true, true),
                                                                                                                                                  (3, 'Carlos', 'Ruiz', 'cruiz', 'carlos.ruiz@sacristan.edu', '{noop}pass1234', true, true, true, true),
                                                                                                                                                  (4, 'Elena', 'Martín', 'emartin', 'elena.martin@sacristan.edu', '{noop}pass1234', true, true, true, true);

INSERT INTO user_roles (user_id, roles) VALUES (2, 'TEACHER'), (3, 'TEACHER'), (4, 'TEACHER');
INSERT INTO teachers (user_id) VALUES (2), (3), (4);

-- =================================================================================
-- 3. ALUMNOS
-- =================================================================================
INSERT INTO users (id, name, last_name, username, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
                                                                                                                                                  (10, 'Sofía', 'López', 'slopez', 'sofia.lopez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (11, 'Mateo', 'Fernández', 'mfernandez', 'mateo.fernandez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (12, 'Valentina', 'Gómez', 'vgomez', 'valentina.gomez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (13, 'Alejandro', 'Díaz', 'adiaz', 'alejandro.diaz@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (20, 'Lucía', 'Pérez', 'lperez', 'lucia.perez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (21, 'Daniel', 'Sánchez', 'dsanchez', 'daniel.sanchez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (22, 'Martina', 'Romero', 'mromero', 'martina.romero@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (30, 'Hugo', 'Alvarez', 'halvarez', 'hugo.alvarez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (31, 'Julia', 'Torres', 'jtorres', 'julia.torres@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (32, 'Pablo', 'Ruiz', 'pruiz_student', 'pablo.ruiz@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (33, 'Emma', 'Vázquez', 'evazquez', 'emma.vazquez@student.sacristan.edu', '{noop}student123', true, true, true, true),
                                                                                                                                                  (34, 'Álvaro', 'Moreno', 'amoreno', 'alvaro.moreno@student.sacristan.edu', '{noop}student123', true, true, true, true);

INSERT INTO user_roles (user_id, roles) VALUES
                                            (10, 'STUDENT'), (11, 'STUDENT'), (12, 'STUDENT'), (13, 'STUDENT'),
                                            (20, 'STUDENT'), (21, 'STUDENT'), (22, 'STUDENT'), (30, 'STUDENT'),
                                            (31, 'STUDENT'), (32, 'STUDENT'), (33, 'STUDENT'), (34, 'STUDENT');

INSERT INTO students (user_id, teacher_id) VALUES
                                               (10, 2), (11, 2), (12, 2), (13, 2),
                                               (20, 3), (21, 3), (22, 3), (30, 4),
                                               (31, 4), (32, 4), (33, 4), (34, 4);

-- =================================================================================
-- 4. CATEGORÍAS
-- =================================================================================
INSERT INTO categories (id, name) VALUES
                                      (1, 'Autocuidado'),
                                      (2, 'Escuela'),
                                      (3, 'Tareas del Hogar'),
                                      (4, 'Emociones'),
                                      (5, 'Ocio y Juego'),
                                      (6, 'Comunicación Social'),
                                      (7, 'Seguridad Vial');

-- =================================================================================
-- 5. SECUENCIAS
-- =================================================================================
-- Categoría 1: Autocuidado
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
                                                                                                   (1, 'Lavarse los dientes', 'Pasos para cepillado correcto', 180000000000, true, 1),
                                                                                                   (4, 'Ir al baño', 'Secuencia de uso del inodoro', 240000000000, true, 1),
                                                                                                   (6, 'Lavarse las manos', 'Higiene antes de comer', 120000000000, true, 1),
                                                                                                   (10, 'Vestirse', 'Ponerse la ropa por la mañana', 600000000000, true, 1),
                                                                                                   (12, 'Ducharse', 'Pasos para la ducha diaria', 900000000000, true, 1),
                                                                                                   (13, 'Comer / Merendar', 'Rutina de alimentación', 900000000000, true, 1);

-- Categoría 2: Escuela
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
                                                                                                   (2, 'Preparar la mochila', 'Cosas para llevar al cole', 300000000000, false, 2),
                                                                                                   (9, 'Entrar en clase', 'Rutina de entrada y saludo', 300000000000, true, 2);

-- Categoría 3: Tareas del Hogar
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
                                                                                                   (5, 'Preparar Merienda', 'Pasos para preparar la merienda', 600000000000, true, 3),
                                                                                                   (7, 'Guardar juguetes', 'Orden en la habitación', 900000000000, false, 3),
                                                                                                   (11, 'Poner la mesa', 'Ayudar antes de cenar', 420000000000, false, 3);

-- Categoría 4: Emociones
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
    (8, 'Calmarse', 'Técnica de la tortuga', 300000000000, true, 4);

-- Categoría 5: Ocio y Juego
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
    (3, 'Pedir jugar', 'Cómo unirse a un juego con amigos', 60000000000, true, 5);

-- Categoría 7: Seguridad Vial
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES
    (16, 'Cruzar la calle', 'Seguridad vial para cruzar', 120000000000, true, 7);

-- =================================================================================
-- 6. PASOS (STEPS)
-- =================================================================================

-- Seq 1: Dientes (Steps 1-4)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (1, 'Poner pasta', 1, 10000000000, 11961, 1),
                                                                                                  (2, 'Cepillar dientes', 2, 120000000000, 2326, 1),
                                                                                                  (3, 'Enjuagar boca', 3, 20000000000, 8560, 1),
                                                                                                  (4, 'Secarse', 4, 10000000000, 2566, 1);

-- Seq 2: Mochila (Steps 5-9)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (5, 'Abrir mochila', 1, 5000000000, 38700, 2),
                                                                                                  (6, 'Meter agenda', 2, 10000000000, 5898, 2),
                                                                                                  (7, 'Meter estuche', 3, 10000000000, 8575, 2),
                                                                                                  (8, 'Meter merienda', 4, 10000000000, 4695, 2),
                                                                                                  (9, 'Cerrar mochila', 5, 5000000000, 38695, 2);

-- Seq 6: Lavarse manos (Steps 10-15)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (10, 'Subir mangas', 1, 10000000000, 28029, 6),
                                                                                                  (11, 'Mojar manos', 2, 5000000000, 6149, 6),
                                                                                                  (12, 'Poner jabón', 3, 5000000000, 31729, 6),
                                                                                                  (13, 'Frotar', 4, 20000000000, 8251, 6),
                                                                                                  (14, 'Aclarar', 5, 10000000000, 21343, 6),
                                                                                                  (15, 'Secar', 6, 10000000000, 2566, 6);

-- Seq 8: Calmarse (Steps 16-19)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (16, 'Parar', 1, 5000000000, 7196, 8),
                                                                                                  (17, 'Respirar hondo', 2, 30000000000, 34377, 8),
                                                                                                  (18, 'Contar hasta 10', 3, 30000000000, 2714, 8),
                                                                                                  (19, 'Pensar solución', 4, 60000000000, 8661, 8);

-- Seq 10: Vestirse (Steps 20-24)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (20, 'Ropa interior', 1, 30000000000, 25680, 10),
                                                                                                  (21, 'Camiseta', 2, 45000000000, 2309, 10),
                                                                                                  (22, 'Pantalón', 3, 45000000000, 2565, 10),
                                                                                                  (23, 'Calcetines', 4, 30000000000, 2298, 10),
                                                                                                  (24, 'Zapatos', 5, 60000000000, 2622, 10);

-- Seq 11: Poner la mesa (Steps 25-29)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (25, 'Mantel', 1, 30000000000, 7008, 11),
                                                                                                  (26, 'Platos', 2, 40000000000, 37947, 11),
                                                                                                  (27, 'Cubiertos', 3, 40000000000, 8545, 11),
                                                                                                  (28, 'Vasos', 4, 30000000000, 2610, 11),
                                                                                                  (29, 'Servilletas', 5, 20000000000, 2569, 11);

-- Seq 4: Ir al baño (Steps 30-34)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (30, 'Bajar ropa', 1, 15000000000, 10143, 4),
                                                                                                  (31, 'Sentarse', 2, 120000000000, 10177, 4),
                                                                                                  (32, 'Limpiarse', 3, 30000000000, 2862, 4),
                                                                                                  (33, 'Tirar cadena', 4, 10000000000, 10184, 4),
                                                                                                  (34, 'Subirse ropa', 5, 15000000000, 10180, 4);

-- Seq 13: Comer / Merendar (Steps 35-39)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (35, 'Sentarse', 1, 10000000000, 32270, 13),
                                                                                                  (36, 'Preparar comida', 2, 60000000000, 7292, 13),
                                                                                                  (37, 'Comer', 3, 420000000000, 2349, 13),
                                                                                                  (38, 'Beber', 4, 60000000000, 2276, 13),
                                                                                                  (39, 'Limpiarse', 5, 15000000000, 17271, 13);

-- Seq 9: Entrar en clase (Steps 40-44)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (40, 'Colgar abrigo', 1, 20000000000, 6986, 9),
                                                                                                  (41, 'Dejar mochila', 2, 20000000000, 5514, 9),
                                                                                                  (42, 'Saludar', 3, 30000000000, 6009, 9),
                                                                                                  (43, 'Sacar material', 4, 45000000000, 8216, 9),
                                                                                                  (44, 'Sentarse', 5, 15000000000, 35033, 9);

-- Seq 12: Ducharse (Steps 45-51)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (45, 'Abrir grifo', 1, 10000000000, 11737, 12),
                                                                                                  (46, 'Mojarse', 2, 30000000000, 6149, 12),
                                                                                                  (47, 'Enjabonar', 3, 60000000000, 21822, 12),
                                                                                                  (48, 'Frotar', 4, 120000000000, 8251, 12),
                                                                                                  (49, 'Aclarar', 5, 60000000000, 21343, 12),
                                                                                                  (50, 'Cerrar grifo', 6, 10000000000, 11739, 12),
                                                                                                  (51, 'Secarse', 7, 60000000000, 2566, 12);

-- Seq 16: Cruzar la calle (Steps 52-56)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES
                                                                                                  (52, 'Parar borde', 1, 10000000000, 7196, 16),
                                                                                                  (53, 'Mirar semáforo', 2, 5000000000, 4958, 16),
                                                                                                  (54, 'Mirar coches', 3, 10000000000, 37101, 16),
                                                                                                  (55, 'Esperar', 4, 15000000000, 8109, 16),
                                                                                                  (56, 'Cruzar', 5, 30000000000, 5976, 16);

-- =================================================================================
-- 7. RUTINAS Y DÍAS
-- =================================================================================
INSERT INTO routines (id, name, category_id, student_user_id) VALUES
                                                                  (1, 'Rutina de Mañana', 1, 10),
                                                                  (2, 'Llegada al Colegio', 2, 10),
                                                                  (3, 'Aseo Personal', 1, 12),
                                                                  (4, 'Rutina de Tarde', 3, 30),
                                                                  (5, 'En la escuela', 2, 20);

-- Días Rutina 1 y 2 (L-V)
INSERT INTO routine_days (routine_id, day_of_the_week) SELECT id, d FROM routines r CROSS JOIN (VALUES ('MONDAY'),('TUESDAY'),('WEDNESDAY'),('THURSDAY'),('FRIDAY')) AS t(d) WHERE r.id IN (1, 2);
-- Días Rutina 3 (Todos)
INSERT INTO routine_days (routine_id, day_of_the_week) SELECT 3, d FROM (VALUES ('MONDAY'),('TUESDAY'),('WEDNESDAY'),('THURSDAY'),('FRIDAY'),('SATURDAY'),('SUNDAY')) AS t(d);
-- Días Rutina 4 (Finde)
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (4, 'SATURDAY'), (4, 'SUNDAY');

-- =================================================================================
-- 8. SECUENCIAS EN RUTINAS
-- =================================================================================
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES
                                                                                     (1, 1, 1, '07:30:00', '07:45:00'),
                                                                                     (2, 1, 2, '08:00:00', '08:15:00'),
                                                                                     (3, 3, 6, '13:30:00', '13:40:00'),
                                                                                     (4, 4, 11, '20:30:00', '20:45:00'),
                                                                                     (5, 5, 9, '09:00:00', '09:10:00'),
                                                                                     (6, 5, 8, '11:00:00', '11:15:00');

-- =================================================================================
-- 9. REPRODUCCIONES (HISTORIAL)
-- =================================================================================
INSERT INTO reproductions (id, sequence_id, student_user_id, started_at, ended_at) VALUES
                                                                                       (1, 1, 10, '2023-10-25 07:35:00', '2023-10-25 07:40:00'),
                                                                                       (2, 2, 10, '2023-10-25 08:05:00', '2023-10-25 08:15:00'),
                                                                                       (3, 11, 30, '2023-10-25 20:30:00', NULL),
                                                                                       (4, 6, 12, '2023-10-25 13:30:00', '2023-10-25 13:33:00');

-- Clicks e Historial de pasos (Ejemplo unificado)
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (1, 'NEXT', 4), (1, 'AUDIO', 2), (2, 'NEXT', 6), (2, 'PREVIOUS', 2);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (1, 1, 15000), (1, 2, 120000), (4, 10, 10000), (4, 11, 5000);

-- =================================================================================
-- 10. REINICIO DE SECUENCIAS PARA FUTUROS INSERTS
-- =================================================================================
ALTER TABLE users ALTER COLUMN id RESTART WITH 100;
ALTER TABLE categories ALTER COLUMN id RESTART WITH 10;
ALTER TABLE sequences ALTER COLUMN id RESTART WITH 50;
ALTER TABLE steps ALTER COLUMN id RESTART WITH 100;
ALTER TABLE routines ALTER COLUMN id RESTART WITH 50;
ALTER TABLE routine_sequence ALTER COLUMN id RESTART WITH 50;
ALTER TABLE reproductions ALTER COLUMN id RESTART WITH 50;