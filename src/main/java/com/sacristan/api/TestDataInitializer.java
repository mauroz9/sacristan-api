package com.sacristan.api;

import com.sacristan.api.global.entities.assignments.routineSequence.RoutineSequence;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.category.CategoryRepository;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.rotuine.RoutineRepository;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceRepository;
import com.sacristan.api.global.entities.content.step.Step;
import com.sacristan.api.global.entities.content.weekDays.DaysOfTheWeek;
import com.sacristan.api.global.entities.tracking.reproduction.Reproduction;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionRepository;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentRepository;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.teacher.TeacherRepository;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.entities.users.user.UserRepository;
import com.sacristan.api.global.entities.users.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    private Teacher createTeacher(String name, String lastName, String username, String email, String password) {
        User user = User.builder().name(name).lastName(lastName).username(username)
                .email(email).password(password).roles(Set.of(Role.TEACHER)).build();
        user = userRepository.save(user);
        return teacherRepository.save(Teacher.builder().user(user).build());
    }

    private Student createStudent(String name, String lastName, String username, String email, String password, Teacher teacher) {
        User user = User.builder().name(name).lastName(lastName).username(username)
                .email(email).password(password).roles(Set.of(Role.STUDENT)).build();
        user = userRepository.save(user);
        return studentRepository.save(Student.builder().user(user).teacher(teacher).build());
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            return;
        }

        // ADMINISTRADOR
        User admin = User.builder()
                .name("Administrador").lastName("Sistema").username("admin")
                .email("admin@sacristan.edu")
                .password("{noop}admin123")
                .roles(Set.of(Role.ADMIN)).build();

        userRepository.save(admin);

        // PROFESORES
        Teacher tAngel = createTeacher("Angel", "Naranjo", "anaranjo", "angel.naranjo@sacristan.edu", "{noop}prof123");
        Teacher tLuismi = createTeacher("Luis Miguel", "Lopez Magaña", "lmlopez", "luismi.lopez@sacristan.edu", "{noop}prof123");

        Teacher tMiguel = createTeacher("Miguel", "Campos", "mcampos", "miguel.campos@sacristan.edu", "{noop}prof123");
        Teacher tJesus = createTeacher("Jesus", "Casanova", "jcasanova", "jesus.casanova@sacristan.edu", "{noop}prof123");

        // ESTUDIANTES
        Student sAntonio = createStudent("Antonio", "Casado", "acasado", "antonio.casado@sacristan.edu", "{noop}student123", tAngel);
        Student sGerman = createStudent("German", "Pastor", "gpastor", "german.pastor@sacristan.edu", "{noop}student123", tLuismi);
        Student sPedroS = createStudent("Pedro", "Serrano", "pserrano", "pedro.serrano@sacristan.edu", "{noop}student123", tMiguel);
        Student sJesus = createStudent("Jesus", "Zamorano", "jzamorano", "jesus.zamorano@sacristan.edu", "{noop}student123", tLuismi);
        Student sPedroM = createStudent("Pedro", "Molina", "pmolina", "pedro.molina@sacristan.edu", "{noop}student123", tMiguel);
        Student sSimon = createStudent("Simon", "Chica", "schica", "simon.chica@sacristan.edu", "{noop}student123", tMiguel);
        Student sRaul = createStudent("Raul", "Linares", "rlinares", "raul.linares@sacristan.edu", "{noop}student123", tMiguel);
        Student sAlvaro = createStudent("Alvaro", "Lazaro", "alazaro", "alvaro.lazaro@sacristan.edu", "{noop}student123", tJesus);
        Student sJavier = createStudent("Javier", "Gomez", "jgomez", "javier.gomez@sacristan.edu", "{noop}student123", tMiguel);
        Student sAlejandro = createStudent("Alejandro", "Rodriguez", "arodriguez", "alejandro.rodriguez@sacristan.edu", "{noop}student123", tLuismi);
        Student sRoberto = createStudent("Roberto", "Montanes", "rmontanes", "roberto.montanes@sacristan.edu", "{noop}student123", tJesus);
        Student sCristian = createStudent("Cristian", "Villalba", "cvillalba", "cristian.villalba@sacristan.edu", "{noop}student123", tAngel);
        Student sGonzalo = createStudent("Gonzalo", "Dios", "gdios", "gonzalo.dios@sacristan.edu", "{noop}student123", tAngel);
        Student sPedroD = createStudent("Pedro", "Diaz", "pdiaz", "pedro.diaz@sacristan.edu", "{noop}student123", tJesus);
        Student sAdrian = createStudent("Adrian", "Caballero", "acaballero", "adrian.caballero@sacristan.edu", "{noop}student123", tLuismi);
        Student sNayat = createStudent("Nayat", "Nmir", "nnmir", "nayat.nmir@sacristan.edu", "{noop}student123", tMiguel);
        Student sCristina = createStudent("Cristina", "Rus", "crus", "cristina.rus@sacristan.edu", "{noop}student123", tJesus);
        Student sMiguel = createStudent("Miguel", "Urquiza", "murquiza", "miguel.urquiza@sacristan.edu", "{noop}student123", tAngel);
        Student sMauro = createStudent("Mauro", "Serrano", "mserrano", "mauro.serrano@sacristan.edu", "{noop}student123", tAngel);
        Student sGabriel = createStudent("Gabriel", "Perez", "gperez", "gabriel.perez@sacristan.edu", "{noop}student123", tLuismi);
        Student sHugo = createStudent("Hugo", "Carmona", "hcarmona", "hugo.carmona@sacristan.edu", "{noop}student123", tJesus);
        Student sPablo = createStudent("Pablo", "Garcia", "pgarcia", "pablo.garcia@sacristan.edu", "{noop}student123", tLuismi);


        // CATEGORÍAS
        Category catHigiene = categoryRepository.save(Category.builder().name("Higiene").build());
        Category catRopa = categoryRepository.save(Category.builder().name("Ropa").build());
        Category catEscuela = categoryRepository.save(Category.builder().name("Escuela").build());
        Category catSeguridad = categoryRepository.save(Category.builder().name("Seguridad").build());
        Category catHogar = categoryRepository.save(Category.builder().name("Hogar").build());
        Category catTransporte = categoryRepository.save(Category.builder().name("Transporte").build());
        Category catAlimentacion = categoryRepository.save(Category.builder().name("Alimentacion").build());
        Category catSocial = categoryRepository.save(Category.builder().name("Social").build());
        Category catEmociones = categoryRepository.save(Category.builder().name("Emociones").build());
        Category catComunidad = categoryRepository.save(Category.builder().name("Comunidad").build());
        Category catSueno = categoryRepository.save(Category.builder().name("Sueño").build());
        Category catOrden = categoryRepository.save(Category.builder().name("Orden").build());
        Category catAutonomia = categoryRepository.save(Category.builder().name("Autonomia").build());
        Category catSalud = categoryRepository.save(Category.builder().name("Salud").build());
        Category catOcio = categoryRepository.save(Category.builder().name("Ocio").build());


        // ==========================================
        // Secuencia 1: Lavarse las manos
        // ==========================================
        Sequence seqManos = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Lavarse las manos")
                .description("Pasos para mantener las manos limpias y sin bacterias antes de comer o después de ir al baño.")
                .estimatedDuration(Duration.ofNanos(180000000000L)) // Mantener siempre
                .allowGoBack(true) // Mantener siempre
                .category(catHigiene)
                .build();

        seqManos.getSteps().add(Step.builder()
                .name("Abrir el grifo y mojarse")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L)) // Mantener siempre
                .arasaacPictogramId(11737)
                .sequence(seqManos)
                .build());

        seqManos.getSteps().add(Step.builder()
                .name("Echarse jabón")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36525)
                .sequence(seqManos)
                .build());

        seqManos.getSteps().add(Step.builder()
                .name("Frotar muy bien")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2443)
                .sequence(seqManos)
                .build());

        seqManos.getSteps().add(Step.builder()
                .name("Enjuagar con agua")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(21343)
                .sequence(seqManos)
                .build());

        seqManos.getSteps().add(Step.builder()
                .name("Cerrar el grifo")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11739)
                .sequence(seqManos)
                .build());

        seqManos.getSteps().add(Step.builder()
                .name("Secarse las manos")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2566)
                .sequence(seqManos)
                .build());

        seqManos = sequenceRepository.save(seqManos);


        // ==========================================
        // Secuencia 2: Cepillarse los dientes
        // ==========================================
        Sequence seqDientes = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cepillarse los dientes")
                .description("Pasos para mantener los dientes limpios y la boca sana después de cada comida.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catHigiene)
                .build();

        seqDientes.getSteps().add(Step.builder()
                .name("Coger el cepillo y la pasta")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(30614)
                .sequence(seqDientes)
                .build());

        seqDientes.getSteps().add(Step.builder()
                .name("Poner pasta en el cepillo")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11961)
                .sequence(seqDientes)
                .build());

        seqDientes.getSteps().add(Step.builder()
                .name("Cepillar los dientes")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2326)
                .sequence(seqDientes)
                .build());

        seqDientes.getSteps().add(Step.builder()
                .name("Escupir la espuma")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(16693)
                .sequence(seqDientes)
                .build());

        seqDientes.getSteps().add(Step.builder()
                .name("Enjuargarse la boca")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8559)
                .sequence(seqDientes)
                .build());

        seqDientes.getSteps().add(Step.builder()
                .name("Limpiar y guardar el cepillo")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11963)
                .sequence(seqDientes)
                .build());

        seqDientes = sequenceRepository.save(seqDientes);


        // ==========================================
        // Secuencia 3: Vestirse por la mañana
        // ==========================================
        Sequence seqVestirse = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Vestirse por la mañana")
                .description("Pasos para cambiarse de ropa al levantarse y prepararse para el día.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catRopa) // Categoría propuesta para diferenciar de higiene
                .build();

        seqVestirse.getSteps().add(Step.builder()
                .name("Quitarse el pijama")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11232)
                .sequence(seqVestirse)
                .build());

        seqVestirse.getSteps().add(Step.builder()
                .name("Ponerse la ropa interior")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(30158) // Se usó el genérico "niño en ropa interior"
                .sequence(seqVestirse)
                .build());

        seqVestirse.getSteps().add(Step.builder()
                .name("Ponerse la camiseta")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(32135)
                .sequence(seqVestirse)
                .build());

        seqVestirse.getSteps().add(Step.builder()
                .name("Ponerse los pantalones")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10180)
                .sequence(seqVestirse)
                .build());

        seqVestirse.getSteps().add(Step.builder()
                .name("Ponerse los calcetines")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36573)
                .sequence(seqVestirse)
                .build());

        seqVestirse.getSteps().add(Step.builder()
                .name("Ponerse los zapatos")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36575)
                .sequence(seqVestirse)
                .build());

        seqVestirse = sequenceRepository.save(seqVestirse);

        // ==========================================
        // Secuencia 4: Rutinas Escolares (Preparar la mochila)
        // ==========================================
        Sequence seqMochila = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Preparar la mochila")
                .description("Pasos para organizar el material escolar y el almuerzo antes de ir a clase.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catEscuela) // Categoría propuesta para rutinas escolares
                .build();

        seqMochila.getSteps().add(Step.builder()
                .name("Abrir la mochila")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38700)
                .sequence(seqMochila)
                .build());

        seqMochila.getSteps().add(Step.builder()
                .name("Meter los cuadernos y la agenda")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8166) // "meter en la mochila" engloba bien la acción
                .sequence(seqMochila)
                .build());

        seqMochila.getSteps().add(Step.builder()
                .name("Meter el estuche")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8575)
                .sequence(seqMochila)
                .build());

        seqMochila.getSteps().add(Step.builder()
                .name("Meter el almuerzo")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(28205)
                .sequence(seqMochila)
                .build());

        seqMochila.getSteps().add(Step.builder()
                .name("Meter la botella de agua")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(4587)
                .sequence(seqMochila)
                .build());

        seqMochila.getSteps().add(Step.builder()
                .name("Cerrar la cremallera")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37430)
                .sequence(seqMochila)
                .build());

        seqMochila = sequenceRepository.save(seqMochila);


        // ==========================================
        // Secuencia 5: Seguridad Vial (Cruzar la calle)
        // ==========================================
        Sequence seqCalle = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cruzar la calle")
                .description("Pasos de seguridad vial para cruzar la calle de forma segura.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catSeguridad) // Categoría propuesta para seguridad vial
                .build();

        seqCalle.getSteps().add(Step.builder()
                .name("Parar antes de la carretera")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(7196)
                .sequence(seqCalle)
                .build());

        seqCalle.getSteps().add(Step.builder()
                .name("Dar la mano a un adulto")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6610)
                .sequence(seqCalle)
                .build());

        seqCalle.getSteps().add(Step.builder()
                .name("Mirar el semáforo")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2567)
                .sequence(seqCalle)
                .build());

        seqCalle.getSteps().add(Step.builder()
                .name("Esperar al muñeco verde")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37108)
                .sequence(seqCalle)
                .build());

        seqCalle.getSteps().add(Step.builder()
                .name("Mirar a los dos lados")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37101)
                .sequence(seqCalle)
                .build());

        seqCalle.getSteps().add(Step.builder()
                .name("Cruzar caminando")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(5976)
                .sequence(seqCalle)
                .build());

        seqCalle = sequenceRepository.save(seqCalle);


        // ==========================================
        // Secuencia 6: Colaboración en Casa (Poner la mesa)
        // ==========================================
        Sequence seqMesa = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Poner la mesa")
                .description("Pasos para ayudar en casa colocando los utensilios necesarios para comer.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catHogar) // Categoría propuesta para tareas del hogar
                .build();

        seqMesa.getSteps().add(Step.builder()
                .name("Poner el mantel")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(7008)
                .sequence(seqMesa)
                .build());

        seqMesa.getSteps().add(Step.builder()
                .name("Poner los platos")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37947)
                .sequence(seqMesa)
                .build());

        seqMesa.getSteps().add(Step.builder()
                .name("Poner los vasos")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2610)
                .sequence(seqMesa)
                .build());

        seqMesa.getSteps().add(Step.builder()
                .name("Poner los tenedores y cucharas")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8545) // "cubiertos" resume bien la acción combinada
                .sequence(seqMesa)
                .build());

        seqMesa.getSteps().add(Step.builder()
                .name("Poner las servilletas")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2569)
                .sequence(seqMesa)
                .build());

        seqMesa.getSteps().add(Step.builder()
                .name("Sentarse a comer")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38706)
                .sequence(seqMesa)
                .build());

        seqMesa = sequenceRepository.save(seqMesa);

        // ==========================================
        // Secuencia 7: Autonomía en el Baño (Cómo ir al baño)
        // ==========================================
        Sequence seqBano = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo ir al baño")
                .description("Pasos para usar el inodoro de forma autónoma y mantener la higiene personal.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catAutonomia) // Categoría propuesta para autonomía personal
                .build();

        seqBano.getSteps().add(Step.builder()
                .name("Bajar el pantalón y la ropa interior")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10143) // "bajar el pantalón"
                .sequence(seqBano)
                .build());

        seqBano.getSteps().add(Step.builder()
                .name("Sentarse en el váter")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10177)
                .sequence(seqBano)
                .build());

        seqBano.getSteps().add(Step.builder()
                .name("Limpiarse con papel higiénico")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10170) // "limpiar el culo" define mejor la acción completa
                .sequence(seqBano)
                .build());

        seqBano.getSteps().add(Step.builder()
                .name("Subir la ropa interior y el pantalón")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10180) // "subir el pantalón"
                .sequence(seqBano)
                .build());

        seqBano.getSteps().add(Step.builder()
                .name("Tirar de la cadena")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10184)
                .sequence(seqBano)
                .build());

        seqBano.getSteps().add(Step.builder()
                .name("Lavarse las manos")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8975)
                .sequence(seqBano)
                .build());

        seqBano = sequenceRepository.save(seqBano);


        // ==========================================
        // Secuencia 8: Hábitos y Orden (Cómo recoger mis juguetes)
        // ==========================================
        Sequence seqJuguetes = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Recoger mis juguetes")
                .description("Pasos para guardar los juguetes y mantener la habitación ordenada al terminar de jugar.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catOrden) // Categoría propuesta para hábitos de orden
                .build();

        seqJuguetes.getSteps().add(Step.builder()
                .name("Escuchar que se acabó el tiempo de jugar")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(35114) // "temporizador"
                .sequence(seqJuguetes)
                .build());

        seqJuguetes.getSteps().add(Step.builder()
                .name("Coger los juguetes del suelo")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8680) // "recoger los juguetes" es muy directo
                .sequence(seqJuguetes)
                .build());

        seqJuguetes.getSteps().add(Step.builder()
                .name("Llevar los juguetes a su caja")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37223)
                .sequence(seqJuguetes)
                .build());

        seqJuguetes.getSteps().add(Step.builder()
                .name("Meter los juguetes dentro")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8166)
                .sequence(seqJuguetes)
                .build());

        seqJuguetes.getSteps().add(Step.builder()
                .name("Cerrar la caja")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2697)
                .sequence(seqJuguetes)
                .build());

        seqJuguetes.getSteps().add(Step.builder()
                .name("Ver que la habitación está limpia")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(32814) // "terminar la tarea"
                .sequence(seqJuguetes)
                .build());

        seqJuguetes = sequenceRepository.save(seqJuguetes);


        // ==========================================
        // Secuencia 9: Rutina de Sueño (Cómo prepararme para dormir)
        // ==========================================
        Sequence seqSueno = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Prepararme para dormir")
                .description("Rutina de noche para relajarse y prepararse para un buen descanso.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catSueno) // Categoría propuesta para sueño/descanso
                .build();

        seqSueno.getSteps().add(Step.builder()
                .name("Ponerse el pijama")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2522)
                .sequence(seqSueno)
                .build());

        seqSueno.getSteps().add(Step.builder()
                .name("Ir a mi cama")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2304) // Usando el objeto "cama"
                .sequence(seqSueno)
                .build());

        seqSueno.getSteps().add(Step.builder()
                .name("Taparse con las sábanas")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(7265) // "tapar"
                .sequence(seqSueno)
                .build());

        seqSueno.getSteps().add(Step.builder()
                .name("Escuchar un cuento")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38631) // "contar un cuento"
                .sequence(seqSueno)
                .build());

        seqSueno.getSteps().add(Step.builder()
                .name("Dar el beso de buenas noches")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6942) // "buenas noches"
                .sequence(seqSueno)
                .build());

        seqSueno.getSteps().add(Step.builder()
                .name("Apagar la luz y cerrar los ojos")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8027) // "apagar la luz"
                .sequence(seqSueno)
                .build());

        seqSueno = sequenceRepository.save(seqSueno);

        // ==========================================
        // Secuencia 10: Higiene Corporal (Cómo ducharme)
        // ==========================================
        Sequence seqDucha = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo ducharme")
                .description("Pasos para lavarse todo el cuerpo en la ducha y mantener una buena higiene personal.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catHigiene)
                .build();

        seqDucha.getSteps().add(Step.builder()
                .name("Quitarse la ropa")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11232) // "desnudar"
                .sequence(seqDucha)
                .build());

        seqDucha.getSteps().add(Step.builder()
                .name("Entrar a la ducha con cuidado")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(39398) // "entrar en la bañera"
                .sequence(seqDucha)
                .build());

        seqDucha.getSteps().add(Step.builder()
                .name("Enjabonar el cuerpo")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(21824)
                .sequence(seqDucha)
                .build());

        seqDucha.getSteps().add(Step.builder()
                .name("Aclarar con agua")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(21345) // "aclarar"
                .sequence(seqDucha)
                .build());

        seqDucha.getSteps().add(Step.builder()
                .name("Salir de la ducha")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(39397) // "salir de la bañera"
                .sequence(seqDucha)
                .build());

        seqDucha.getSteps().add(Step.builder()
                .name("Secarse con la toalla")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2566) // "secar"
                .sequence(seqDucha)
                .build());

        seqDucha = sequenceRepository.save(seqDucha);


        // ==========================================
        // Secuencia 11: Salidas a la Comunidad (Comprar en el supermercado)
        // ==========================================
        Sequence seqSupermercado = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Comprar en el supermercado")
                .description("Pasos para hacer la compra de alimentos de forma ordenada.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catComunidad) // Categoría propuesta para salidas al entorno
                .build();

        seqSupermercado.getSteps().add(Step.builder()
                .name("Coger el carro de la compra")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(30618)
                .sequence(seqSupermercado)
                .build());

        seqSupermercado.getSteps().add(Step.builder()
                .name("Buscar los alimentos")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6946)
                .sequence(seqSupermercado)
                .build());

        seqSupermercado.getSteps().add(Step.builder()
                .name("Meter las cosas en el carro")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8166)
                .sequence(seqSupermercado)
                .build());

        seqSupermercado.getSteps().add(Step.builder()
                .name("Esperar la cola en la caja")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(35187) // "esperar en fila"
                .sequence(seqSupermercado)
                .build());

        seqSupermercado.getSteps().add(Step.builder()
                .name("Poner la compra en la cinta")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6989) // "poner"
                .sequence(seqSupermercado)
                .build());

        seqSupermercado.getSteps().add(Step.builder()
                .name("Guardar todo en bolsas")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2872) // "ordenar" o guardar
                .sequence(seqSupermercado)
                .build());

        seqSupermercado = sequenceRepository.save(seqSupermercado);


        // ==========================================
        // Secuencia 12: Regulación Emocional (Calmarme cuando me enfado)
        // ==========================================
        Sequence seqCalma = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Calmarme cuando me enfado")
                .description("Pasos de relajación para recuperar la tranquilidad cuando siento mucho enfado.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catEmociones) // Categoría propuesta para gestión emocional
                .build();

        seqCalma.getSteps().add(Step.builder()
                .name("Reconocer que estoy enfadado")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(21820) // "enfadado"
                .sequence(seqCalma)
                .build());

        seqCalma.getSteps().add(Step.builder()
                .name("Ir a un lugar tranquilo")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(32826) // "rincón de la calma"
                .sequence(seqCalma)
                .build());

        seqCalma.getSteps().add(Step.builder()
                .name("Cerrar los ojos")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(10146)
                .sequence(seqCalma)
                .build());

        seqCalma.getSteps().add(Step.builder()
                .name("Coger aire por la nariz")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6602) // "inspirar"
                .sequence(seqCalma)
                .build());

        seqCalma.getSteps().add(Step.builder()
                .name("Soltar el aire por la boca")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(30102) // "espirar"
                .sequence(seqCalma)
                .build());

        seqCalma.getSteps().add(Step.builder()
                .name("Pedir ayuda o un abrazo")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(4550) // "abrazo"
                .sequence(seqCalma)
                .build());

        seqCalma = sequenceRepository.save(seqCalma);

        // ==========================================
        // Secuencia 13: Habilidades Sociales (Cómo pedir un juguete a un amigo)
        // ==========================================
        Sequence seqJugueteAmigo = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo pedir un juguete")
                .description("Pasos para interactuar con los compañeros, pedir las cosas educadamente y compartir.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catSocial) // Categoría propuesta para habilidades sociales
                .build();

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Mirar al amigo")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36333) // "mirar a los ojos"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Acercarse despacio")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(5359) // "acercar"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Pedir el juguete por favor")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(11251) // "pedir"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Esperar mi turno")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(7158) // "mi turno"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Jugar con el juguete")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2439) // "jugar"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo.getSteps().add(Step.builder()
                .name("Decir gracias al terminar")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38783) // "agradecer"
                .sequence(seqJugueteAmigo)
                .build());

        seqJugueteAmigo = sequenceRepository.save(seqJugueteAmigo);


        // ==========================================
        // Secuencia 14: Rutinas de Alimentación (Cómo comer en la mesa)
        // ==========================================
        Sequence seqComer = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo comer en la mesa")
                .description("Pasos para mantener una buena postura, autonomía y modales a la hora de comer.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catAlimentacion) // Categoría propuesta para alimentación
                .build();

        seqComer.getSteps().add(Step.builder()
                .name("Sentarse bien en la silla")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(32270) // "sentar correctamente"
                .sequence(seqComer)
                .build());

        seqComer.getSteps().add(Step.builder()
                .name("Coger el tenedor o la cuchara")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36629) // "coger el tenedor"
                .sequence(seqComer)
                .build());

        seqComer.getSteps().add(Step.builder()
                .name("Coger la comida del plato")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36654) // "pinchar la comida"
                .sequence(seqComer)
                .build());

        seqComer.getSteps().add(Step.builder()
                .name("Meter la comida en la boca")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2349) // "comer"
                .sequence(seqComer)
                .build());

        seqComer.getSteps().add(Step.builder()
                .name("Masticar con la boca cerrada")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37071) // "masticador"
                .sequence(seqComer)
                .build());

        seqComer.getSteps().add(Step.builder()
                .name("Limpiarse con la servilleta")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2569) // "servilleta"
                .sequence(seqComer)
                .build());

        seqComer = sequenceRepository.save(seqComer);


        // ==========================================
        // Secuencia 15: Transiciones y Transporte (Cómo viajar en el coche)
        // ==========================================
        Sequence seqCoche = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo viajar en el coche")
                .description("Pasos para subir al vehículo y realizar un viaje tranquilo y seguro.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catTransporte) // Categoría propuesta para transportes/transiciones
                .build();

        seqCoche.getSteps().add(Step.builder()
                .name("Caminar hasta el coche")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(3251) // "caminar"
                .sequence(seqCoche)
                .build());

        seqCoche.getSteps().add(Step.builder()
                .name("Abrir la puerta")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(24597) // "abrir la puerta"
                .sequence(seqCoche)
                .build());

        seqCoche.getSteps().add(Step.builder()
                .name("Subir a la sillita")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37104) // "sentar en el asiento de seguridad"
                .sequence(seqCoche)
                .build());

        seqCoche.getSteps().add(Step.builder()
                .name("Abrochar el cinturón")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(34669) // "abrochar el cinturón"
                .sequence(seqCoche)
                .build());

        seqCoche.getSteps().add(Step.builder()
                .name("Estar tranquilo en el viaje")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37828) // "tranquilo"
                .sequence(seqCoche)
                .build());

        seqCoche.getSteps().add(Step.builder()
                .name("Bajar cuando el coche pare")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36958) // "salir del coche"
                .sequence(seqCoche)
                .build());

        seqCoche = sequenceRepository.save(seqCoche);

        // ==========================================
        // Secuencia 16: Cómo ir a la consulta del médico
        // ==========================================
        Sequence seqMedico = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo ir a la consulta del médico")
                .description("Pasos para saber qué hacer al ir al doctor para una revisión o cuando nos sentimos mal.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catSalud) // Categoría propuesta para temas médicos y bienestar
                .build();

        seqMedico.getSteps().add(Step.builder()
                .name("Sentarse en la sala de espera")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(24634) // "sala de espera"
                .sequence(seqMedico)
                .build());

        seqMedico.getSteps().add(Step.builder()
                .name("Entrar a la consulta al escuchar mi nombre")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6491) // "entrar"
                .sequence(seqMedico)
                .build());

        seqMedico.getSteps().add(Step.builder()
                .name("Saludar al doctor")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(4740) // "saludo"
                .sequence(seqMedico)
                .build());

        seqMedico.getSteps().add(Step.builder()
                .name("Sentarse en la camilla")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(5937) // "camilla"
                .sequence(seqMedico)
                .build());

        seqMedico.getSteps().add(Step.builder()
                .name("Dejar que el doctor me escuche el pecho")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(30692) // "auscultar"
                .sequence(seqMedico)
                .build());

        seqMedico.getSteps().add(Step.builder()
                .name("Despedirse y decir adiós")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(21793) // "despedirse"
                .sequence(seqMedico)
                .build());

        seqMedico = sequenceRepository.save(seqMedico);


        // ==========================================
        // Secuencia 17: Qué hacer si me hago una herida
        // ==========================================
        Sequence seqHerida = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Qué hacer si me hago una herida")
                .description("Pasos de primeros auxilios y calma para curar una pequeña herida o raspón.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catSalud)
                .build();

        seqHerida.getSteps().add(Step.builder()
                .name("Avisar a mamá, papá o al profe")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(27630) // "llamar/avisar"
                .sequence(seqHerida)
                .build());

        seqHerida.getSteps().add(Step.builder()
                .name("Enseñar dónde me duele")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(6612) // "indicar"
                .sequence(seqHerida)
                .build());

        seqHerida.getSteps().add(Step.builder()
                .name("Lavar la herida con agua y jabón")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8977) // "lavar"
                .sequence(seqHerida)
                .build());

        seqHerida.getSteps().add(Step.builder()
                .name("Secar la piel con cuidado")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2566) // "secar"
                .sequence(seqHerida)
                .build());

        seqHerida.getSteps().add(Step.builder()
                .name("Poner una tirita sobre la herida")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38452) // "poner una tirita"
                .sequence(seqHerida)
                .build());

        seqHerida.getSteps().add(Step.builder()
                .name("Dar un abrazo para sentirme mejor")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(4550) // "abrazo"
                .sequence(seqHerida)
                .build());

        seqHerida = sequenceRepository.save(seqHerida);


        // ==========================================
        // Secuencia 18: Cómo jugar en los columpios del parque
        // ==========================================
        Sequence seqParque = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo jugar en los columpios del parque")
                .description("Pasos para divertirse en el parque de forma segura y respetando los turnos.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catOcio) // Categoría propuesta para tiempo libre y juegos
                .build();

        seqParque.getSteps().add(Step.builder()
                .name("Llegar al parque con un adulto")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36145) // "parque infantil"
                .sequence(seqParque)
                .build());

        seqParque.getSteps().add(Step.builder()
                .name("Elegir un columpio o tobogán")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(36101) // "columpio"
                .sequence(seqParque)
                .build());

        seqParque.getSteps().add(Step.builder()
                .name("Esperar en la fila si hay otro niño jugando")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(35187) // "esperar en fila"
                .sequence(seqParque)
                .build());

        seqParque.getSteps().add(Step.builder()
                .name("Subir al columpio cuando esté libre")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(24725) // "subir"
                .sequence(seqParque)
                .build());

        seqParque.getSteps().add(Step.builder()
                .name("Agarrarse fuerte con las dos manos")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(34185) // "agarrar"
                .sequence(seqParque)
                .build());

        seqParque.getSteps().add(Step.builder()
                .name("Bajar con cuidado al terminar")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(24723) // "bajar"
                .sequence(seqParque)
                .build());

        seqParque = sequenceRepository.save(seqParque);


    // ==========================================
    // Secuencia 19: Cómo lavarme el pelo
    // ==========================================
        Sequence seqLavarPelo = Sequence
                .builder()
                .steps(new ArrayList<>())
                .title("Cómo lavarme el pelo")
                .description("Rutina específica para limpiar el cabello durante el baño o la ducha.")
                .estimatedDuration(Duration.ofNanos(180000000000L))
                .allowGoBack(true)
                .category(catHigiene)
                .build();

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Echar la cabeza hacia atrás")
                .position(1)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(9004) // "inclinar"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Mojar el pelo con el agua")
                .position(2)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8976) // "lavar el pelo / mojar"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Poner un poco de champú en las manos")
                .position(3)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(37409) // "echar champú o jabón"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Frotar la cabeza haciendo espuma")
                .position(4)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(38555) // "enjabonar el pelo"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Aclarar con agua hasta que no quede jabón")
                .position(5)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(8978) // "aclarar el pelo"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo.getSteps().add(Step.builder()
                .name("Secar el pelo con la toalla")
                .position(6)
                .estimatedDuration(Duration.ofNanos(10000000000L))
                .arasaacPictogramId(2566) // "secar"
                .sequence(seqLavarPelo)
                .build());

        seqLavarPelo = sequenceRepository.save(seqLavarPelo);

        // ==========================================
        // Asignación de secuencias a alumnos
        // ==========================================

        // Antonio (3 secuencias - Rutinas de mañana)
        sAntonio.getSequences().add(seqManos);
        sAntonio.getSequences().add(seqDientes);
        sAntonio.getSequences().add(seqVestirse);

        // Germán (3 secuencias - Entorno escolar y hogar)
        sGerman.getSequences().add(seqMochila);
        sGerman.getSequences().add(seqCalle);
        sGerman.getSequences().add(seqMesa);

        // Pedro Serrano (4 secuencias - Higiene y noche)
        sPedroS.getSequences().add(seqBano);
        sPedroS.getSequences().add(seqJuguetes);
        sPedroS.getSequences().add(seqSueno);
        sPedroS.getSequences().add(seqDucha);

        // Jesús (2 secuencias - Comunidad y emociones)
        sJesus.getSequences().add(seqSupermercado);
        sJesus.getSequences().add(seqCalma);

        // Pedro Molina (3 secuencias - Social y transporte)
        sPedroM.getSequences().add(seqJugueteAmigo);
        sPedroM.getSequences().add(seqComer);
        sPedroM.getSequences().add(seqCoche);

        // Simón (5 secuencias - Salud, ocio e higiene básica)
        sSimon.getSequences().add(seqMedico);
        sSimon.getSequences().add(seqHerida);
        sSimon.getSequences().add(seqParque);
        sSimon.getSequences().add(seqLavarPelo);
        sSimon.getSequences().add(seqManos);

        // Raúl (2 secuencias - Autonomía básica)
        sRaul.getSequences().add(seqDientes);
        sRaul.getSequences().add(seqVestirse);

        // Álvaro (3 secuencias - Entorno escolar y hogar)
        sAlvaro.getSequences().add(seqMochila);
        sAlvaro.getSequences().add(seqCalle);
        sAlvaro.getSequences().add(seqMesa);

        // Javier (4 secuencias - Higiene y orden)
        sJavier.getSequences().add(seqBano);
        sJavier.getSequences().add(seqJuguetes);
        sJavier.getSequences().add(seqSueno);
        sJavier.getSequences().add(seqDucha);

        // Alejandro (2 secuencias - Regulación y comunidad)
        sAlejandro.getSequences().add(seqSupermercado);
        sAlejandro.getSequences().add(seqCalma);

        // Roberto (3 secuencias - Social y alimentación)
        sRoberto.getSequences().add(seqJugueteAmigo);
        sRoberto.getSequences().add(seqComer);
        sRoberto.getSequences().add(seqCoche);

        // Cristian (4 secuencias - Salud y ocio)
        sCristian.getSequences().add(seqMedico);
        sCristian.getSequences().add(seqHerida);
        sCristian.getSequences().add(seqParque);
        sCristian.getSequences().add(seqLavarPelo);

        // Gonzalo (2 secuencias - Tareas básicas)
        sGonzalo.getSequences().add(seqManos);
        sGonzalo.getSequences().add(seqMesa);

        // Pedro Díaz (3 secuencias - Higiene y vestimenta)
        sPedroD.getSequences().add(seqDientes);
        sPedroD.getSequences().add(seqVestirse);
        sPedroD.getSequences().add(seqBano);

        // Adrián (5 secuencias - Combinación amplia)
        sAdrian.getSequences().add(seqMochila);
        sAdrian.getSequences().add(seqCalle);
        sAdrian.getSequences().add(seqMesa);
        sAdrian.getSequences().add(seqSueno);
        sAdrian.getSequences().add(seqDucha);

        // Nayat (2 secuencias - Regulación y comunidad)
        sNayat.getSequences().add(seqSupermercado);
        sNayat.getSequences().add(seqCalma);

        // Cristina (3 secuencias - Social y alimentación)
        sCristina.getSequences().add(seqJugueteAmigo);
        sCristina.getSequences().add(seqComer);
        sCristina.getSequences().add(seqCoche);

        // Miguel (4 secuencias - Salud e higiene específica)
        sMiguel.getSequences().add(seqMedico);
        sMiguel.getSequences().add(seqHerida);
        sMiguel.getSequences().add(seqParque);
        sMiguel.getSequences().add(seqLavarPelo);

        // Mauro (2 secuencias - Higiene básica)
        sMauro.getSequences().add(seqManos);
        sMauro.getSequences().add(seqDientes);

        // Gabriel (3 secuencias - Rutina de mañana)
        sGabriel.getSequences().add(seqVestirse);
        sGabriel.getSequences().add(seqMochila);
        sGabriel.getSequences().add(seqCalle);

        // Hugo (4 secuencias - Noche y orden)
        sHugo.getSequences().add(seqBano);
        sHugo.getSequences().add(seqJuguetes);
        sHugo.getSequences().add(seqSueno);
        sHugo.getSequences().add(seqDucha);

        // Pablo (2 secuencias - Regulación y compras)
        sPablo.getSequences().add(seqSupermercado);
        sPablo.getSequences().add(seqCalma);

        // ============================================================================
        // CREACIÓN DE SECUENCIAS PREVIAS PARA LAS RUTINAS
        // ============================================================================

        // Secuencia: Asamblea Matutina
        Sequence seqAsamblea = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Asamblea de la mañana")
                .description("Saludar a los compañeros y ver qué día es hoy.")
                .estimatedDuration(Duration.ofMinutes(20))
                .allowGoBack(true)
                .category(catEscuela)
                .build();
        seqAsamblea.getSteps().add(Step.builder().name("Sentarse en círculo").position(1).estimatedDuration(Duration.ofNanos(30000000000L)).arasaacPictogramId(38706).sequence(seqAsamblea).build());
        seqAsamblea.getSteps().add(Step.builder().name("Dar los buenos días").position(2).estimatedDuration(Duration.ofNanos(30000000000L)).arasaacPictogramId(6941).sequence(seqAsamblea).build());
        seqAsamblea.getSteps().add(Step.builder().name("Ver el tiempo que hace").position(3).estimatedDuration(Duration.ofNanos(60000000000L)).arasaacPictogramId(28521).sequence(seqAsamblea).build());
        seqAsamblea = sequenceRepository.save(seqAsamblea);

        // Secuencia: Trabajo en mesa (Fichas)
        Sequence seqTrabajoMesa = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Trabajo en mesa")
                .description("Hacer la ficha de clase concentrado.")
                .estimatedDuration(Duration.ofMinutes(40))
                .allowGoBack(true)
                .category(catEscuela)
                .build();
        seqTrabajoMesa.getSteps().add(Step.builder().name("Sentarse en la silla").position(1).estimatedDuration(Duration.ofNanos(20000000000L)).arasaacPictogramId(32270).sequence(seqTrabajoMesa).build());
        seqTrabajoMesa.getSteps().add(Step.builder().name("Coger el lápiz").position(2).estimatedDuration(Duration.ofNanos(20000000000L)).arasaacPictogramId(2395).sequence(seqTrabajoMesa).build());
        seqTrabajoMesa.getSteps().add(Step.builder().name("Hacer la ficha").position(3).estimatedDuration(Duration.ofMinutes(35)).arasaacPictogramId(35832).sequence(seqTrabajoMesa).build());
        seqTrabajoMesa = sequenceRepository.save(seqTrabajoMesa);

        // Secuencia: Almuerzo en clase
        Sequence seqAlmuerzoClase = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Tomar el almuerzo")
                .description("Comer el bocadillo o fruta antes del recreo.")
                .estimatedDuration(Duration.ofMinutes(20))
                .allowGoBack(true)
                .category(catAlimentacion)
                .build();
        seqAlmuerzoClase.getSteps().add(Step.builder().name("Sacar el almuerzo").position(1).estimatedDuration(Duration.ofNanos(30000000000L)).arasaacPictogramId(28205).sequence(seqAlmuerzoClase).build());
        seqAlmuerzoClase.getSteps().add(Step.builder().name("Comer tranquilo").position(2).estimatedDuration(Duration.ofMinutes(15)).arasaacPictogramId(2349).sequence(seqAlmuerzoClase).build());
        seqAlmuerzoClase.getSteps().add(Step.builder().name("Beber agua").position(3).estimatedDuration(Duration.ofNanos(40000000000L)).arasaacPictogramId(1229).sequence(seqAlmuerzoClase).build());
        seqAlmuerzoClase = sequenceRepository.save(seqAlmuerzoClase);

        // Secuencia: Educación Física
        Sequence seqGimnasia = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Clase de Gimnasia")
                .description("Hacer deporte en el pabellón.")
                .estimatedDuration(Duration.ofMinutes(45))
                .allowGoBack(true)
                .category(catEscuela)
                .build();
        seqGimnasia.getSteps().add(Step.builder().name("Ir al pabellón").position(1).estimatedDuration(Duration.ofMinutes(5)).arasaacPictogramId(3251).sequence(seqGimnasia).build());
        seqGimnasia.getSteps().add(Step.builder().name("Calentar el cuerpo").position(2).estimatedDuration(Duration.ofMinutes(10)).arasaacPictogramId(37083).sequence(seqGimnasia).build());
        seqGimnasia.getSteps().add(Step.builder().name("Jugar con pelota").position(3).estimatedDuration(Duration.ofMinutes(30)).arasaacPictogramId(2446).sequence(seqGimnasia).build());
        seqGimnasia = sequenceRepository.save(seqGimnasia);

        // Secuencia: Rincones de Juego
        Sequence seqRincones = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Juego por rincones")
                .description("Elegir un rincón de la clase para jugar libremente.")
                .estimatedDuration(Duration.ofMinutes(45))
                .allowGoBack(true)
                .category(catOcio)
                .build();
        seqRincones.getSteps().add(Step.builder().name("Elegir rincón").position(1).estimatedDuration(Duration.ofNanos(60000000000L)).arasaacPictogramId(36149).sequence(seqRincones).build());
        seqRincones.getSteps().add(Step.builder().name("Compartir juguetes").position(2).estimatedDuration(Duration.ofMinutes(40)).arasaacPictogramId(15361).sequence(seqRincones).build());
        seqRincones.getSteps().add(Step.builder().name("Recoger todo").position(3).estimatedDuration(Duration.ofMinutes(5)).arasaacPictogramId(8680).sequence(seqRincones).build());
        seqRincones = sequenceRepository.save(seqRincones);

        // Secuencia: Dibujo libre
        Sequence seqDibujo = Sequence.builder()
                .steps(new ArrayList<>())
                .title("Dibujo Creativo")
                .description("Preparar materiales y dibujar")
                .estimatedDuration(Duration.ofMinutes(45))
                .allowGoBack(true)
                .category(catOcio)
                .build();

        seqDibujo.getSteps().add(Step.builder().name("Sacar folios").position(1).estimatedDuration(Duration.ofNanos(30000000000L)).arasaacPictogramId(2398).sequence(seqDibujo).build());
        seqDibujo.getSteps().add(Step.builder().name("Elegir colores").position(2).estimatedDuration(Duration.ofNanos(60000000000L)).arasaacPictogramId(5968).sequence(seqDibujo).build());
        seqDibujo.getSteps().add(Step.builder().name("Dibujar libre").position(3).estimatedDuration(Duration.ofNanos(1800000000000L)).arasaacPictogramId(8088).sequence(seqDibujo).build());

        sequenceRepository.save(seqDibujo);

        // ============================================================================
        // CREACIÓN DE RUTINAS (Foco: 09:35 AM)
        // ============================================================================
        Set<DaysOfTheWeek> diasLaborables = Set.of(
                DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.WEDNESDAY,
                DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY
        );

        // ----------------------------------------------------------------------------
        // RUTINA 1: Trabajo Académico (Lunes a Viernes)
        // Transición de la asamblea al trabajo en mesa exactamente a las 09:35
        // ----------------------------------------------------------------------------
        Routine rutTrabajoMatutino = Routine.builder()
                .name("Rutina de Mañana - Trabajo Académico")
                .category(catEscuela)
                .sequences(new ArrayList<>())
                .daysOfTheWeek(diasLaborables)
                .build();

        rutTrabajoMatutino.getSequences().add(RoutineSequence.builder()
                .routine(rutTrabajoMatutino)
                .sequence(seqAsamblea)
                .startTime(LocalTime.of(9, 15))
                .endTime(LocalTime.of(9, 35)) // Termina justo a las 09:35
                .build());

        rutTrabajoMatutino.getSequences().add(RoutineSequence.builder()
                .routine(rutTrabajoMatutino)
                .sequence(seqTrabajoMesa)
                .startTime(LocalTime.of(9, 35)) // Empieza justo a las 09:35
                .endTime(LocalTime.of(10, 15))
                .build());

        rutTrabajoMatutino.getSequences().add(RoutineSequence.builder()
                .routine(rutTrabajoMatutino)
                .sequence(seqManos) // Reutilizamos la de lavarse las manos
                .startTime(LocalTime.of(10, 15))
                .endTime(LocalTime.of(10, 25))
                .build());

        routineRepository.save(rutTrabajoMatutino);

        // ----------------------------------------------------------------------------
        // RUTINA 2: Desayuno Temprano e Higiene (Especial para algunos alumnos)
        // Para alumnos que almuerzan a las 09:35 AM
        // ----------------------------------------------------------------------------
        Routine rutAlmuerzoTemprano = Routine.builder()
                .name("Rutina de Almuerzo Temprano")
                .category(catAlimentacion)
                .sequences(new ArrayList<>())
                .daysOfTheWeek(diasLaborables)
                .build();

        rutAlmuerzoTemprano.getSequences().add(RoutineSequence.builder()
                .routine(rutAlmuerzoTemprano)
                .sequence(seqManos) // Lavarse antes de comer
                .startTime(LocalTime.of(9, 25))
                .endTime(LocalTime.of(9, 35))
                .build());

        rutAlmuerzoTemprano.getSequences().add(RoutineSequence.builder()
                .routine(rutAlmuerzoTemprano)
                .sequence(seqAlmuerzoClase)
                .startTime(LocalTime.of(9, 35)) // Actividad central a las 09:35
                .endTime(LocalTime.of(9, 55))
                .build());

        rutAlmuerzoTemprano.getSequences().add(RoutineSequence.builder()
                .routine(rutAlmuerzoTemprano)
                .sequence(seqDientes) // Reutilizamos la de cepillarse
                .startTime(LocalTime.of(9, 55))
                .endTime(LocalTime.of(10, 10))
                .build());

        routineRepository.save(rutAlmuerzoTemprano);

        // ----------------------------------------------------------------------------
        // RUTINA 3: Educación Física (Martes y Jueves)
        // La clase fuerte empieza a las 09:35 AM
        // ----------------------------------------------------------------------------
        Routine rutGimnasiaSemanal = Routine.builder()
                .name("Rutina de Educación Física")
                .category(catEscuela)
                .sequences(new ArrayList<>())
                .daysOfTheWeek(Set.of(DaysOfTheWeek.TUESDAY, DaysOfTheWeek.THURSDAY))
                .build();

        rutGimnasiaSemanal.getSequences().add(RoutineSequence.builder()
                .routine(rutGimnasiaSemanal)
                .sequence(seqBano) // Ir al baño antes de bajar al pabellón
                .startTime(LocalTime.of(9, 20))
                .endTime(LocalTime.of(9, 35))
                .build());

        rutGimnasiaSemanal.getSequences().add(RoutineSequence.builder()
                .routine(rutGimnasiaSemanal)
                .sequence(seqGimnasia)
                .startTime(LocalTime.of(9, 35)) // Inicio de clase a las 09:35
                .endTime(LocalTime.of(10, 20))
                .build());

        rutGimnasiaSemanal.getSequences().add(RoutineSequence.builder()
                .routine(rutGimnasiaSemanal)
                .sequence(seqManos) // Asearse al terminar
                .startTime(LocalTime.of(10, 20))
                .endTime(LocalTime.of(10, 30))
                .build());

        routineRepository.save(rutGimnasiaSemanal);

        // ----------------------------------------------------------------------------
        // RUTINA 4: Viernes de Ocio (Juego Libre)
        // Relajación y rincones a partir de las 09:35 AM
        // ----------------------------------------------------------------------------
        Routine rutViernesOcio = Routine.builder()
                .name("Rutina Viernes - Rincones y Arte")
                .category(catOcio)
                .sequences(new ArrayList<>())
                .daysOfTheWeek(Set.of(DaysOfTheWeek.FRIDAY))
                .build();

        rutViernesOcio.getSequences().add(RoutineSequence.builder()
                .routine(rutViernesOcio)
                .sequence(seqAsamblea)
                .startTime(LocalTime.of(9, 00))
                .endTime(LocalTime.of(9, 35)) // Asamblea un poco más larga los viernes
                .build());

        rutViernesOcio.getSequences().add(RoutineSequence.builder()
                .routine(rutViernesOcio)
                .sequence(seqRincones)
                .startTime(LocalTime.of(9, 35)) // Empieza el juego a las 09:35
                .endTime(LocalTime.of(10, 20))
                .build());

        rutViernesOcio.getSequences().add(RoutineSequence.builder()
                .routine(rutViernesOcio)
                .sequence(seqDibujo) // Reutilizando la tuya original
                .startTime(LocalTime.of(10, 20))
                .endTime(LocalTime.of(11, 00))
                .build());

        routineRepository.save(rutViernesOcio);

        Routine rutPrueba = Routine.builder()
                .name("Actividad de Martes")
                .category(catOcio)
                .daysOfTheWeek(
                        Set.of(DaysOfTheWeek.valueOf(LocalDate.now().getDayOfWeek().name()))
                )
                .build();

        rutPrueba.getSequences().add(
                RoutineSequence.builder()
                        .routine(rutPrueba)
                        .sequence(seqDibujo)
                        .startTime(LocalTime.now().minusMinutes(10))
                        .endTime(LocalTime.now().plusMinutes(35))
                        .build());

        rutPrueba.getSequences().add(
                RoutineSequence.builder()
                .routine(rutPrueba)
                .sequence(seqDibujo)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusMinutes(45))
                .build());

        rutPrueba.getSequences().add(
                RoutineSequence.builder()
                        .routine(rutPrueba)
                        .sequence(seqDibujo)
                        .startTime(LocalTime.now().plusMinutes(10))
                        .endTime(LocalTime.now().plusMinutes(55))
                        .build());

        routineRepository.save(rutPrueba);



        // ============================================================================
        // ASIGNACIÓN ALEATORIA DE RUTINAS A ESTUDIANTES
        // ============================================================================

        // Antonio
        sAntonio.getRoutines().add(rutPrueba);
        sAntonio.getRoutines().add(rutTrabajoMatutino);
        sAntonio.getRoutines().add(rutViernesOcio);
        studentRepository.save(sAntonio);

        // Germán
        sGerman.getRoutines().add(rutGimnasiaSemanal);
        sGerman.getRoutines().add(rutPrueba);
        studentRepository.save(sGerman);

        // Pedro Serrano
        sPedroS.getRoutines().add(rutAlmuerzoTemprano);
        sPedroS.getRoutines().add(rutTrabajoMatutino);
        studentRepository.save(sPedroS);

        // Jesús
        sJesus.getRoutines().add(rutViernesOcio);
        sJesus.getRoutines().add(rutPrueba);
        studentRepository.save(sJesus);

        // Pedro Molina
        sPedroM.getRoutines().add(rutTrabajoMatutino);
        sPedroM.getRoutines().add(rutGimnasiaSemanal);
        studentRepository.save(sPedroM);

        // Simón
        sSimon.getRoutines().add(rutAlmuerzoTemprano);
        sSimon.getRoutines().add(rutPrueba);
        studentRepository.save(sSimon);

        // Raúl
        sRaul.getRoutines().add(rutGimnasiaSemanal);
        sRaul.getRoutines().add(rutViernesOcio);
        studentRepository.save(sRaul);

        // Álvaro
        sAlvaro.getRoutines().add(rutTrabajoMatutino);
        sAlvaro.getRoutines().add(rutPrueba);
        studentRepository.save(sAlvaro);

        // Javier
        sJavier.getRoutines().add(rutAlmuerzoTemprano);
        sJavier.getRoutines().add(rutViernesOcio);
        studentRepository.save(sJavier);

        // Alejandro
        sAlejandro.getRoutines().add(rutTrabajoMatutino);
        sAlejandro.getRoutines().add(rutGimnasiaSemanal);
        studentRepository.save(sAlejandro);

        // Roberto
        sRoberto.getRoutines().add(rutAlmuerzoTemprano);
        sRoberto.getRoutines().add(rutPrueba);
        studentRepository.save(sRoberto);

        // Cristian
        sCristian.getRoutines().add(rutViernesOcio);
        sCristian.getRoutines().add(rutTrabajoMatutino);
        studentRepository.save(sCristian);

        // Gonzalo
        sGonzalo.getRoutines().add(rutGimnasiaSemanal);
        sGonzalo.getRoutines().add(rutAlmuerzoTemprano);
        studentRepository.save(sGonzalo);

        // Pedro Díaz
        sPedroD.getRoutines().add(rutPrueba);
        sPedroD.getRoutines().add(rutViernesOcio);
        studentRepository.save(sPedroD);

        // Adrián
        sAdrian.getRoutines().add(rutTrabajoMatutino);
        sAdrian.getRoutines().add(rutAlmuerzoTemprano);
        studentRepository.save(sAdrian);

        // Nayat
        sNayat.getRoutines().add(rutGimnasiaSemanal);
        sNayat.getRoutines().add(rutPrueba);
        studentRepository.save(sNayat);

        // Cristina
        sCristina.getRoutines().add(rutViernesOcio);
        sCristina.getRoutines().add(rutAlmuerzoTemprano);
        studentRepository.save(sCristina);

        // Miguel
        sMiguel.getRoutines().add(rutTrabajoMatutino);
        sMiguel.getRoutines().add(rutGimnasiaSemanal);
        studentRepository.save(sMiguel);

        // Mauro
        sMauro.getRoutines().add(rutPrueba);
        sMauro.getRoutines().add(rutAlmuerzoTemprano);
        studentRepository.save(sMauro);

        // Gabriel
        sGabriel.getRoutines().add(rutViernesOcio);
        sGabriel.getRoutines().add(rutTrabajoMatutino);
        studentRepository.save(sGabriel);

        // Hugo
        sHugo.getRoutines().add(rutGimnasiaSemanal);
        sHugo.getRoutines().add(rutPrueba);
        studentRepository.save(sHugo);

        // Pablo
        sPablo.getRoutines().add(rutAlmuerzoTemprano);
        sPablo.getRoutines().add(rutViernesOcio);
        studentRepository.save(sPablo);

        // ============================================================================
        // GENERACIÓN DE REPRODUCCIONES HISTÓRICAS (últimas 6 semanas laborables)
        // ============================================================================
        generateHistoricalReproductions(List.of(
                sAntonio, sGerman, sPedroS, sJesus, sPedroM, sSimon,
                sRaul, sAlvaro, sJavier, sAlejandro, sRoberto, sCristian,
                sGonzalo, sPedroD, sAdrian, sNayat, sCristina, sMiguel,
                sMauro, sGabriel, sHugo, sPablo
        ));

    }

    /**
     * Genera reproducciones COMPLETED para cada alumno, recorriendo sus rutinas
     * asignadas y comprobando si el día de la semana coincide.
     * Se simulan las últimas 6 semanas laborables con un 80% de probabilidad
     * de completar cada secuencia cada día.
     */
    private void generateHistoricalReproductions(List<Student> students) {
        Random rnd = new Random(42); // Semilla fija para reproducibilidad
        LocalDate today = LocalDate.now();
        LocalDate from = today.minusWeeks(6);

        for (LocalDate date = from; !date.isAfter(today.minusDays(1)); date = date.plusDays(1)) {
            // Solo días laborables
            int dow = date.getDayOfWeek().getValue(); // 1=Lun … 5=Vie
            if (dow > 5) continue;

            DaysOfTheWeek dayEnum = DaysOfTheWeek.valueOf(date.getDayOfWeek().name());

            for (Student student : students) {
                for (Routine routine : student.getRoutines()) {
                    // Verificar que la rutina está programada ese día
                    if (!routine.getDaysOfTheWeek().contains(dayEnum)) continue;

                    for (RoutineSequence rs : routine.getSequences()) {
                        // 80% de probabilidad de completar
                        if (rnd.nextDouble() > 0.80) continue;

                        LocalTime start = rs.getStartTime();
                        LocalTime end = rs.getEndTime();

                        // Añadir variación de ±2 minutos para realismo
                        int startOffsetSecs = rnd.nextInt(120) - 60;
                        int durationSecs = (int) Duration.between(start, end).toSeconds();
                        if (durationSecs <= 0) durationSecs = 600;

                        LocalDateTime startedAt = date.atTime(start).plusSeconds(startOffsetSecs);
                        LocalDateTime endedAt = startedAt.plusSeconds(durationSecs + rnd.nextInt(60));

                        reproductionRepository.save(
                                Reproduction.builder()
                                        .student(student)
                                        .routineSequence(rs)
                                        .status(Status.COMPLETED)
                                        .startedAt(startedAt)
                                        .endedAt(endedAt)
                                        .build()
                        );
                    }
                }
            }
        }
    }
}