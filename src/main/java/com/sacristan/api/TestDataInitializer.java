package com.sacristan.api;

import com.sacristan.api.global.models.*;
import com.sacristan.api.global.models.user.*;
import com.sacristan.api.global.models.user.extra.Role;
import com.sacristan.api.global.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final SequenceRepository sequenceRepository;
    private final RoutineRepository routineRepository;
    private final ReproductionRepository reproductionRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Skip initialization if data already exists
        if (userRepository.count() > 0) {
            return;
        }

        /* =================================================================================
           1. ADMINISTRATOR
           ================================================================================= */
        User admin = User.builder()
                .name("Administrador").lastName("Sistema").username("admin")
                .email("admin@sacristan.edu").password("{noop}admin123")
                .roles(Set.of(Role.ADMIN)).build();
        userRepository.save(admin);

        /* =================================================================================
           2. TEACHERS
           ================================================================================= */
        Teacher tLaura = createTeacher("Laura", "García", "lgarcia", "laura.garcia@sacristan.edu", "{noop}pass1234");
        Teacher tCarlos = createTeacher("Carlos", "Ruiz", "cruiz", "carlos.ruiz@sacristan.edu", "{noop}pass1234");
        Teacher tElena = createTeacher("Elena", "Martín", "emartin", "elena.martin@sacristan.edu", "{noop}pass1234");

        /* =================================================================================
           3. STUDENTS
           ================================================================================= */
        Student sSofia = createStudent("Sofía", "López", "slopez", "sofia.lopez@sacristan.edu", "{noop}student123", tLaura);
        Student sMateo = createStudent("Mateo", "Fernández", "mfernandez", "mateo.fernandez@sacristan.edu", "{noop}student123", tLaura);
        Student sValentina = createStudent("Valentina", "Gómez", "vgomez", "valentina.gomez@sacristan.edu", "{noop}student123", tLaura);
        Student sAlejandro = createStudent("Alejandro", "Díaz", "adiaz", "alejandro.diaz@sacristan.edu", "{noop}student123", tLaura);

        Student sLucia = createStudent("Lucía", "Pérez", "lperez", "lucia.perez@sacristan.edu", "{noop}student123", tCarlos);
        Student sDaniel = createStudent("Daniel", "Sánchez", "dsanchez", "daniel.sanchez@sacristan.edu", "{noop}student123", tCarlos);
        Student sMartina = createStudent("Martina", "Romero", "mromero", "martina.romero@sacristan.edu", "{noop}student123", tCarlos);

        Student sHugo = createStudent("Hugo", "Alvarez", "halvarez", "hugo.alvarez@sacristan.edu", "{noop}student123", tElena);
        Student sJulia = createStudent("Julia", "Torres", "jtorres", "julia.torres@sacristan.edu", "{noop}student123", tElena);
        Student sPablo = createStudent("Pablo", "Ruiz", "pruiz_student", "pablo.ruiz@sacristan.edu", "{noop}student123", tElena);
        Student sEmma = createStudent("Emma", "Vázquez", "evazquez", "emma.vazquez@sacristan.edu", "{noop}student123", tElena);
        Student sAlvaro = createStudent("Álvaro", "Moreno", "amoreno", "alvaro.moreno@sacristan.edu", "{noop}student123", tElena);

        /* =================================================================================
           4. CATEGORIES
           ================================================================================= */
        Category catAutocuidado = categoryRepository.save(Category.builder().name("Autocuidado").build());
        Category catEscuela = categoryRepository.save(Category.builder().name("Escuela").build());
        Category catTareasHogar = categoryRepository.save(Category.builder().name("Tareas del Hogar").build());
        Category catEmociones = categoryRepository.save(Category.builder().name("Emociones").build());
        Category catOcioJuego = categoryRepository.save(Category.builder().name("Ocio y Juego").build());
        Category catComunicacion = categoryRepository.save(Category.builder().name("Comunicación Social").build());
        Category catSeguridadVial = categoryRepository.save(Category.builder().name("Seguridad Vial").build());

        /* =================================================================================
           5 & 6. SEQUENCES AND STEPS (Cascade saved)
           ================================================================================= */

        // Seq 1: Dientes
        Sequence seqDientes = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Lavarse los dientes")
                .description("Pasos para cepillado correcto")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catAutocuidado)
                .build();
        seqDientes.getSteps().add(Step.builder().name("Poner pasta").position(1).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(11961).sequence(seqDientes).build());
        seqDientes.getSteps().add(Step.builder().name("Cepillar dientes").position(2).estimatedDuration(Duration.ofNanos(120000000000L)).arasaacPictogramId(2326).sequence(seqDientes).build());
        seqDientes.getSteps().add(Step.builder().name("Enjuagar boca").position(3).estimatedDuration(Duration.ofNanos(20000000000L)).arasaacPictogramId(8560).sequence(seqDientes).build());
        seqDientes.getSteps().add(Step.builder().name("Secarse").position(4).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(2566).sequence(seqDientes).build());
        seqDientes = sequenceRepository.save(seqDientes);

        // Seq 2: Mochila
        Sequence seqMochila = Sequence.builder().steps(new ArrayList<>()).title("Preparar la mochila").description("Cosas para llevar al cole").estimatedDuration(Duration.ofNanos(300000000000L)).allowGoBack(false).category(catEscuela).build();
        seqMochila.getSteps().add(Step.builder().name("Abrir mochila").position(1).estimatedDuration(Duration.ofNanos(5000000000L)).arasaacPictogramId(38700).sequence(seqMochila).build());
        seqMochila.getSteps().add(Step.builder().name("Meter agenda").position(2).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(5898).sequence(seqMochila).build());
        seqMochila.getSteps().add(Step.builder().name("Meter estuche").position(3).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(8575).sequence(seqMochila).build());
        seqMochila.getSteps().add(Step.builder().name("Meter merienda").position(4).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(4695).sequence(seqMochila).build());
        seqMochila.getSteps().add(Step.builder().name("Cerrar mochila").position(5).estimatedDuration(Duration.ofNanos(5000000000L)).arasaacPictogramId(38695).sequence(seqMochila).build());
        seqMochila = sequenceRepository.save(seqMochila);

        // Seq 6: Lavarse manos
        Sequence seqManos = Sequence.builder().steps(new ArrayList<>()).title("Lavarse las manos").description("Higiene antes de comer").estimatedDuration(Duration.ofNanos(120000000000L)).allowGoBack(true).category(catAutocuidado).build();
        seqManos.getSteps().add(Step.builder().name("Subir mangas").position(1).estimatedDuration(Duration.ofNanos(10000000000L)).arasaacPictogramId(28029).sequence(seqManos).build());
        seqManos.getSteps().add(Step.builder().name("Mojar manos").position(2).estimatedDuration(Duration.ofNanos(5000000000L)).arasaacPictogramId(6149).sequence(seqManos).build());
        seqManos = sequenceRepository.save(seqManos);

        // Seq 8: Calmarse
        Sequence seqCalmarse = Sequence.builder().steps(new ArrayList<>()).title("Calmarse").description("Técnica de la tortuga").estimatedDuration(Duration.ofNanos(300000000000L)).allowGoBack(true).category(catEmociones).build();
        seqCalmarse.getSteps().add(Step.builder().name("Parar").position(1).estimatedDuration(Duration.ofNanos(5000000000L)).arasaacPictogramId(7196).sequence(seqCalmarse).build());
        seqCalmarse = sequenceRepository.save(seqCalmarse);

        // Seq 9: Entrar en clase
        Sequence seqEntrarClase = Sequence.builder().steps(new ArrayList<>()).title("Entrar en clase").description("Rutina de entrada y saludo").estimatedDuration(Duration.ofNanos(300000000000L)).allowGoBack(true).category(catEscuela).build();
        seqEntrarClase = sequenceRepository.save(seqEntrarClase);

        // Seq 11: Poner la mesa
        Sequence seqMesa = Sequence.builder().steps(new ArrayList<>()).title("Poner la mesa").description("Ayudar antes de cenar").estimatedDuration(Duration.ofNanos(420000000000L)).allowGoBack(false).category(catTareasHogar).build();
        seqMesa.getSteps().add(Step.builder().name("Mantel").position(1).estimatedDuration(Duration.ofNanos(300000000000L)).arasaacPictogramId(7008).sequence(seqMesa).build());
        seqMesa = sequenceRepository.save(seqMesa);

        /* =================================================================================
           7 & 8. ROUTINES AND ROUTINE_SEQUENCES
           ================================================================================= */
        Set<DaysOfTheWeek> weekdays = Set.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY);
        Set<DaysOfTheWeek> allDays = Set.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY, DaysOfTheWeek.SUNDAY);
        Set<DaysOfTheWeek> weekend = Set.of(DaysOfTheWeek.SATURDAY, DaysOfTheWeek.SUNDAY);

        Routine rutManana = Routine.builder().name("Rutina de Mañana").category(catAutocuidado).daysOfTheWeek(weekdays).build();
        rutManana.getSequences().add(RoutineSequence.builder().routine(rutManana).sequence(seqDientes).startTime(LocalTime.of(7, 30)).endTime(LocalTime.of(7, 45)).build());
        rutManana.getSequences().add(RoutineSequence.builder().routine(rutManana).sequence(seqMochila).startTime(LocalTime.of(8, 0)).endTime(LocalTime.of(8, 15)).build());
        rutManana = routineRepository.save(rutManana);

        Routine rutColegio = Routine.builder().name("Llegada al Colegio").category(catEscuela).daysOfTheWeek(weekdays).build();
        rutColegio = routineRepository.save(rutColegio);

        Routine rutAseo = Routine.builder().name("Aseo Personal").category(catAutocuidado).daysOfTheWeek(allDays).build();
        rutAseo.getSequences().add(RoutineSequence.builder().routine(rutAseo).sequence(seqManos).startTime(LocalTime.of(13, 30)).endTime(LocalTime.of(13, 40)).build());
        rutAseo = routineRepository.save(rutAseo);

        Routine rutTarde = Routine.builder().name("Rutina de Tarde").category(catTareasHogar).daysOfTheWeek(weekend).build();
        rutTarde.getSequences().add(RoutineSequence.builder().routine(rutTarde).sequence(seqMesa).startTime(LocalTime.of(20, 30)).endTime(LocalTime.of(20, 45)).build());
        rutTarde = routineRepository.save(rutTarde);

        Routine rutEscuela = Routine.builder().name("En la escuela").category(catEscuela).daysOfTheWeek(weekdays).build();
        rutEscuela.getSequences().add(RoutineSequence.builder().routine(rutEscuela).sequence(seqEntrarClase).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(9, 10)).build());
        rutEscuela.getSequences().add(RoutineSequence.builder().routine(rutEscuela).sequence(seqCalmarse).startTime(LocalTime.of(11, 0)).endTime(LocalTime.of(11, 15)).build());
        rutEscuela = routineRepository.save(rutEscuela);

        // Assign routines to students
        sSofia.getRoutines().add(rutManana);
        sSofia.getRoutines().add(rutColegio);
        studentRepository.save(sSofia);

        sValentina.getRoutines().add(rutAseo);
        studentRepository.save(sValentina);

        sHugo.getRoutines().add(rutTarde);
        studentRepository.save(sHugo);

        sLucia.getRoutines().add(rutEscuela);
        studentRepository.save(sLucia);

        sAlejandro.getRoutines().add(rutManana);
        sAlejandro.getRoutines().add(rutAseo);
        studentRepository.save(sAlejandro);

        /* =================================================================================
           8.5 ASSIGN STANDALONE SEQUENCES TO STUDENTS
           ================================================================================= */
        // Sofía gets assigned her morning sequences directly
        sSofia.assignSequence(seqDientes);
        sSofia.assignSequence(seqMochila);
        studentRepository.save(sSofia);

        // Mateo gets assigned hygiene and emotion regulation
        sMateo.assignSequence(seqManos);
        sMateo.assignSequence(seqCalmarse);
        studentRepository.save(sMateo);

        // Hugo gets assigned home chores and emotion regulation
        sHugo.assignSequence(seqMesa);
        sHugo.assignSequence(seqCalmarse);
        studentRepository.save(sHugo);

        // Lucía gets assigned school-related sequences
        sLucia.assignSequence(seqEntrarClase);
        studentRepository.save(sLucia);

        sAlejandro.assignSequence(seqEntrarClase);
        sAlejandro.assignSequence(seqDientes);
        studentRepository.save(sAlejandro);

        /* =================================================================================
           9. REPRODUCTIONS (HISTORY)
           ================================================================================= */
        Reproduction rep1 = Reproduction.builder()
                .sequence(seqDientes).student(sSofia)
                .startedAt(LocalDateTime.of(2023, 10, 25, 7, 35))
                .endedAt(LocalDateTime.of(2023, 10, 25, 7, 40)).build();
        rep1.getButtonsClicks().put("NEXT", 4);
        rep1.getButtonsClicks().put("AUDIO", 2);
        // Using dynamically generated step IDs
        rep1.getReproductionTime().put(seqDientes.getSteps().get(0).getId(), 15000L);
        rep1.getReproductionTime().put(seqDientes.getSteps().get(1).getId(), 120000L);
        reproductionRepository.save(rep1);

        Reproduction rep2 = Reproduction.builder()
                .sequence(seqMochila).student(sSofia)
                .startedAt(LocalDateTime.of(2023, 10, 25, 8, 5))
                .endedAt(LocalDateTime.of(2023, 10, 25, 8, 15)).build();
        rep2.getButtonsClicks().put("NEXT", 6);
        rep2.getButtonsClicks().put("PREVIOUS", 2);
        reproductionRepository.save(rep2);

        Reproduction rep3 = Reproduction.builder()
                .sequence(seqMesa).student(sHugo)
                .startedAt(LocalDateTime.of(2023, 10, 25, 20, 30))
                .endedAt(null).build();
        reproductionRepository.save(rep3);


        /* =================================================================================
           NEW: CUSTOM AFTER-SCHOOL ROUTINE FOR ALEJANDRO
           ================================================================================= */

        // 1. Create a new Sequence for the routine
        Sequence seqDibujo = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Dibujo Creativo")
                .description("Preparar materiales y dibujar")
                .estimatedDuration(Duration.ofMinutes(45))
                .allowGoBack(true)
                .category(catOcioJuego)
                .build();

        seqDibujo.getSteps().add(Step.builder().name("Sacar folios").position(1).estimatedDuration(Duration.ofNanos(30000000000L)).arasaacPictogramId(7535).sequence(seqDibujo).build());
        seqDibujo.getSteps().add(Step.builder().name("Elegir colores").position(2).estimatedDuration(Duration.ofNanos(60000000000L)).arasaacPictogramId(25357).sequence(seqDibujo).build());
        seqDibujo.getSteps().add(Step.builder().name("Dibujar libre").position(3).estimatedDuration(Duration.ofNanos(1800000000000L)).arasaacPictogramId(2341).sequence(seqDibujo).build());

        sequenceRepository.save(seqDibujo);

        Routine rutTardeMartes = Routine.builder()
                .name("Actividad de Martes")
                .category(catOcioJuego)
                .daysOfTheWeek(
                        Set.of(DaysOfTheWeek.valueOf(LocalDate.now().getDayOfWeek().name()))
                )
                .build();

        // 3. Add the Sequence to the Routine with the specific time
        rutTardeMartes.getSequences().add(RoutineSequence.builder()
                .routine(rutTardeMartes)
                .sequence(seqDibujo)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusMinutes(45))
                .build());

        routineRepository.save(rutTardeMartes);

        // 4. Assign it specifically to Alejandro
        sAlejandro.getRoutines().add(rutTardeMartes);
        studentRepository.save(sAlejandro);

    }

    // Helper method to create and save a Teacher
    private Teacher createTeacher(String name, String lastName, String username, String email, String password) {
        User user = User.builder().name(name).lastName(lastName).username(username)
                .email(email).password(password).roles(Set.of(Role.TEACHER)).build();
        user = userRepository.save(user);
        return teacherRepository.save(Teacher.builder().user(user).build());
    }

    // Helper method to create and save a Student
    private Student createStudent(String name, String lastName, String username, String email, String password, Teacher teacher) {
        User user = User.builder().name(name).lastName(lastName).username(username)
                .email(email).password(password).roles(Set.of(Role.STUDENT)).build();
        user = userRepository.save(user);
        return studentRepository.save(Student.builder().user(user).teacher(teacher).build());
    }


}