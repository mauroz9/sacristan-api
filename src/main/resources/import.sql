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

-- =================================================================================
-- 4. CATEGORÍAS
-- =================================================================================
INSERT INTO categories (id, name) VALUES (1, 'Autocuidado');
INSERT INTO categories (id, name) VALUES (2, 'Escuela');
INSERT INTO categories (id, name) VALUES (3, 'Tareas del Hogar');
INSERT INTO categories (id, name) VALUES (4, 'Emociones');
INSERT INTO categories (id, name) VALUES (5, 'Ocio y Juego');
INSERT INTO categories (id, name) VALUES (6, 'Comunicación Social');

-- =================================================================================
-- 5. SECUENCIAS
-- =================================================================================
-- Nota: se asigna para vincular secuencias a alumnos específicos según tu modelo JPA.
-- Duraciones en nanosegundos (1 min = 60000000000)

-- --- Secuencias para Sofía (ID 10) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (1, 'Lavarse los dientes', 'Pasos para cepillado correcto', 180000000000, true, 1);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (2, 'Preparar la mochila', 'Cosas para llevar al cole', 300000000000, false, 2);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (3, 'Pedir jugar', 'Cómo unirse a un juego con amigos', 60000000000, true, 5);

-- --- Secuencias para Mateo (ID 11) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (4, 'Ir al baño', 'Secuencia de uso del inodoro', 240000000000, true, 1);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (5, 'Merienda', 'Pasos para preparar la merienda', 600000000000, true, 3);

-- --- Secuencias para Valentina (ID 12) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (6, 'Lavarse las manos', 'Higiene antes de comer', 120000000000, true, 1);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (7, 'Guardar juguetes', 'Orden en la habitación', 900000000000, false, 3);

-- --- Secuencias para Lucía Pérez (ID 20) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (8, 'Calmarse', 'Técnica de la tortuga', 300000000000, true, 4);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (9, 'Entrar en clase', 'Rutina de entrada y saludo', 60000000000, false, 2);

-- --- Secuencias para Hugo (ID 30) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (10, 'Vestirse', 'Ponerse la ropa por la mañana', 600000000000, true, 1);
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (11, 'Poner la mesa', 'Ayudar antes de cenar', 420000000000, false, 3);

-- --- Secuencias para Julia (ID 31) ---
INSERT INTO sequences (id, title, description, estimated_duration, allow_go_back, category_id) VALUES (12, 'Ducharse', 'Pasos para la ducha diaria', 900000000000, true, 1);


-- =================================================================================
-- 6. PASOS (STEPS)
-- =================================================================================

-- Pasos para Seq 1: Lavarse los dientes (Sofía)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (1, 'Poner pasta', 1, 10000000000, 5432, 1);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (2, 'Cepillar dientes', 2, 120000000000, 5433, 1);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (3, 'Enjuagar boca', 3, 20000000000, 5434, 1);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (4, 'Secarse', 4, 10000000000, 5435, 1);

-- Pasos para Seq 2: Mochila (Sofía)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (5, 'Abrir mochila', 1, 5000000000, 2301, 2);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (6, 'Meter agenda', 2, 10000000000, 2302, 2);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (7, 'Meter estuche', 3, 10000000000, 2303, 2);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (8, 'Meter merienda', 4, 10000000000, 2304, 2);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (9, 'Cerrar mochila', 5, 5000000000, 2305, 2);

-- Pasos para Seq 6: Lavarse manos (Valentina)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (10, 'Subir mangas', 1, 10000000000, 6701, 6);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (11, 'Mojar manos', 2, 5000000000, 6702, 6);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (12, 'Poner jabón', 3, 5000000000, 6703, 6);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (13, 'Frotar', 4, 20000000000, 6704, 6);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (14, 'Aclarar', 5, 10000000000, 6705, 6);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (15, 'Secar', 6, 10000000000, 6706, 6);

-- Pasos para Seq 8: Calmarse (Lucía)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (16, 'Parar', 1, 5000000000, 8801, 8);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (17, 'Respirar hondo', 2, 30000000000, 8802, 8);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (18, 'Contar hasta 10', 3, 30000000000, 8803, 8);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (19, 'Pensar solución', 4, 60000000000, 8804, 8);

-- Pasos para Seq 10: Vestirse (Hugo)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (20, 'Ropa interior', 1, 30000000000, 4401, 10);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (21, 'Camiseta', 2, 45000000000, 4402, 10);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (22, 'Pantalón', 3, 45000000000, 4403, 10);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (23, 'Calcetines', 4, 30000000000, 4404, 10);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (24, 'Zapatos', 5, 60000000000, 4405, 10);

-- Pasos para Seq 11: Poner la mesa (Hugo)
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (25, 'Mantel', 1, 30000000000, 9901, 11);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (26, 'Platos', 2, 40000000000, 9902, 11);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (27, 'Cubiertos', 3, 40000000000, 9903, 11);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (28, 'Vasos', 4, 30000000000, 9904, 11);
INSERT INTO steps (id, name, position, estimated_duration, arasaac_pictogram_id, sequence_id) VALUES (29, 'Servilletas', 5, 20000000000, 9905, 11);

-- =================================================================================
-- 7. RUTINAS
-- =================================================================================
-- Rutinas asignadas a estudiantes (student_user_id)

-- Rutina 1: Mañana (Sofía - ID 10)
INSERT INTO routines (id, name, category_id, student_user_id) VALUES (1, 'Rutina de Mañana', 1, 10);

-- Rutina 2: Colegio (Sofía - ID 10)
INSERT INTO routines (id, name, category_id, student_user_id) VALUES (2, 'Llegada al Colegio', 2, 10);

-- Rutina 3: Higiene (Valentina - ID 12)
INSERT INTO routines (id, name, category_id, student_user_id) VALUES (3, 'Aseo Personal', 1, 12);

-- Rutina 4: Tarde (Hugo - ID 30)
INSERT INTO routines (id, name, category_id, student_user_id) VALUES (4, 'Rutina de Tarde', 3, 30);

-- Rutina 5: Colegio (Lucía - ID 20)
INSERT INTO routines (id, name, category_id, student_user_id) VALUES (5, 'En la escuela', 2, 20);

-- =================================================================================
-- 8. DÍAS DE RUTINA (Routine Days)
-- =================================================================================

-- Rutina 1 (Sofía Mañana): Lunes a Viernes
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (1, 'MONDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (1, 'TUESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (1, 'WEDNESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (1, 'THURSDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (1, 'FRIDAY');

-- Rutina 2 (Sofía Colegio): Lunes a Viernes
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (2, 'MONDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (2, 'TUESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (2, 'WEDNESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (2, 'THURSDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (2, 'FRIDAY');

-- Rutina 3 (Valentina Aseo): Todos los días
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'MONDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'TUESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'WEDNESDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'THURSDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'FRIDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'SATURDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (3, 'SUNDAY');

-- Rutina 4 (Hugo Tarde): Fin de semana
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (4, 'SATURDAY');
INSERT INTO routine_days (routine_id, day_of_the_week) VALUES (4, 'SUNDAY');

-- =================================================================================
-- 9. SECUENCIAS EN RUTINAS (Routine Sequence)
-- =================================================================================

-- Rutina 1 (Sofía): Lavarse dientes (7:30) y Mochila (8:00)
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (1, 1, 1, '07:30:00', '07:45:00');
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (2, 1, 2, '08:00:00', '08:15:00');

-- Rutina 3 (Valentina): Lavarse manos antes de comer (13:30)
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (3, 3, 6, '13:30:00', '13:40:00');

-- Rutina 4 (Hugo): Poner la mesa (20:30)
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (4, 4, 11, '20:30:00', '20:45:00');

-- Rutina 5 (Lucía): Entrar en clase (09:00) y Calmarse (11:00)
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (5, 5, 9, '09:00:00', '09:10:00');
INSERT INTO routine_sequence (id, routine_id, sequence_id, start_time, end_time) VALUES (6, 5, 8, '11:00:00', '11:15:00');

-- =================================================================================
-- 10. REPRODUCCIONES (Historial)
-- =================================================================================

-- Historial Sofía (ID 10) - Lavarse dientes ayer
INSERT INTO reproductions (id, sequence_id, student_user_id, started_at, ended_at) VALUES (1, 1, 10, '2023-10-25 07:35:00', '2023-10-25 07:40:00');
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (1, 'NEXT', 4);
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (1, 'AUDIO', 2);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (1, 1, 15000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (1, 2, 120000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (1, 3, 30000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (1, 4, 10000);
INSERT INTO reproduction_step_counts (reproduction_id, step_id, reproductions_count) VALUES (1, 1, 1);
INSERT INTO reproduction_step_counts (reproduction_id, step_id, reproductions_count) VALUES (1, 2, 1);
INSERT INTO reproduction_step_counts (reproduction_id, step_id, reproductions_count) VALUES (1, 3, 1);
INSERT INTO reproduction_step_counts (reproduction_id, step_id, reproductions_count) VALUES (1, 4, 1);

-- Historial Sofía (ID 10) - Mochila ayer (con errores/retrocesos)
INSERT INTO reproductions (id, sequence_id, student_user_id, started_at, ended_at) VALUES (2, 2, 10, '2023-10-25 08:05:00', '2023-10-25 08:15:00');
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (2, 'NEXT', 6);
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (2, 'PREVIOUS', 2);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (2, 5, 20000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (2, 6, 30000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (2, 7, 30000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (2, 8, 40000);

-- Historial Hugo (ID 30) - Poner la mesa (sin terminar)
INSERT INTO reproductions (id, sequence_id, student_user_id, started_at, ended_at) VALUES (3, 11, 30, '2023-10-25 20:30:00', NULL);
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (3, 'NEXT', 2);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (3, 25, 30000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (3, 26, 45000);

-- Historial Valentina (ID 12) - Lavarse manos (Perfecto)
INSERT INTO reproductions (id, sequence_id, student_user_id, started_at, ended_at) VALUES (4, 6, 12, '2023-10-25 13:30:00', '2023-10-25 13:33:00');
INSERT INTO reproduction_clicks (reproduction_id, button_name, times_clicked) VALUES (4, 'NEXT', 6);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 10, 10000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 11, 5000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 12, 5000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 13, 20000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 14, 10000);
INSERT INTO reproduction_step_times (reproduction_id, step_id, active_time_ms) VALUES (4, 15, 10000);

-- Reinicio de secuencia para IDs futuros
ALTER TABLE categories ALTER COLUMN id RESTART WITH 10;
ALTER TABLE sequences ALTER COLUMN id RESTART WITH 50;
ALTER TABLE steps ALTER COLUMN id RESTART WITH 100;
ALTER TABLE routines ALTER COLUMN id RESTART WITH 50;
ALTER TABLE routine_sequence ALTER COLUMN id RESTART WITH 50;
ALTER TABLE reproductions ALTER COLUMN id RESTART WITH 50;