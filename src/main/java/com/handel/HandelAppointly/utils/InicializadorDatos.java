package com.handel.HandelAppointly.utils;
import com.handel.HandelAppointly.entidades.*;
import com.handel.HandelAppointly.enums.NivelAcceso;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.repositorios.*;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class InicializadorDatos implements CommandLineRunner {

    // Paciente
    private final PacienteRepositorio pacienteRepositorio;

    // Administrador
    private final AdministradorRepositorio administradorRepositorio;

    // Especialidad
    private final EspecialidadRepositorio especialidadRepositorio;

    // Divisa
    private final DivisaRepositorio divisaRepositorio;
    private final DivisaServicio divisaServicio;

    // Doctor
    private final DoctorRepositorio doctorRepositorio;

    // Medicina
    private final MedicinaRepositorio medicinaRepositorio;

    @Override
    public void run(String... args) {
        inicializarPacientes();
        inicializarAdministradores();
        inicializarEspecialidades();
        inicializarDivisas();
        inicializarDoctores();
        inicializarMedicinas();
    }

    public void inicializarPacientes() {
        if (pacienteRepositorio.count() == 0) {
            List<Paciente> pacientesIniciales = List.of(
                    Paciente.builder()
                            .nombre("Zadkiel").apellido("Barron Dominguez").email("barrondominguezzadkiel@gmail.comm")
                            .contrasena("Zadkiel#2009").numeroTelefono("5621724816")
                            .rol(Rol.PACIENTE).fechaNacimiento(LocalDate.of(2009,10,17))
                            .contactoEmergenciaNombre("Leonardo").contactoEmergenciaTelefono("5512174406")
                            .build(),
                    Paciente.builder()
                            .nombre("Leonardo Damian").apellido("Gonzalez Rendón").email("gonzalez.leonardo.ky@gmail.com")
                            .contrasena("Leonardo#2009").numeroTelefono("5512174406")
                            .rol(Rol.PACIENTE).fechaNacimiento(LocalDate.of(2009,9,23))
                            .contactoEmergenciaNombre("Zadkiel").contactoEmergenciaTelefono("5621724816")
                            .build()
            );

            pacienteRepositorio.saveAll(pacientesIniciales);
            log.info("Base de Datos Inicializada: Pacientes iniciadas");
        }
    }

    public void inicializarAdministradores() {
        if (administradorRepositorio.count() == 0) {
            List<Administrador> administradoresIniciales = List.of(
                    Administrador.builder()
                            .nombre("Handel").apellido("Appointly").email("admin@handel.com")
                            .contrasena("HandelAppointlyAdmin").numeroTelefono("5505822346")
                            .rol(Rol.ADMINISTRADOR).nivelAcceso(NivelAcceso.SUPER_ADMIN)
                            .build(),
                    Administrador.builder()
                            .nombre("Leonardo").apellido("Gonzalez Rendón").email("LeonardoGR@handel.com")
                            .contrasena("HandelAppointlyLGR").numeroTelefono("5514264911")
                            .rol(Rol.ADMINISTRADOR).nivelAcceso(NivelAcceso.ADMIN)
                            .build()
            );

            administradorRepositorio.saveAll(administradoresIniciales);
            log.info("Base de Datos Inicializada: Administradores iniciadas");
        }
    }

    public void inicializarEspecialidades() {
        if (especialidadRepositorio.count() == 0)  {
            List<Especialidad> especialidadesIniciales = List.of(
                    new Especialidad(null ,"Cardiologia", "Especialidad médica que se ocupa del estudio del corazón"),
                    new Especialidad(null ,"Pediatria", "Especialidad medica dedicada al cuidado integral de la salud de niños y adolescentes"),
                    new Especialidad(null ,"Dermatologia", "Especialidad médica que estudia la piel")
            );

            especialidadRepositorio.saveAll(especialidadesIniciales);
            log.info("Base de Datos Inicializada: Especialidades básicas añadidas");
        }
    }

    public void inicializarDivisas() {
        if (divisaRepositorio.count() == 0) {
            List<Divisa> divisasIniciales = List.of(
                    new Divisa("USD", "$", "US Dollar", BigDecimal.ONE, null),
                    new Divisa("MXN", "$", "Mexican Peso", BigDecimal.ZERO, null),
                    new Divisa("EUR", "€", "Euro", BigDecimal.ZERO, null),
                    new Divisa("GBP", "£", "British Pound", BigDecimal.ZERO, null),
                    new Divisa("CAD", "$", "Canadian Dollar", BigDecimal.ZERO, null),
                    new Divisa("NZD", "$", "New Zealand Dollar", BigDecimal.ZERO, null),
                    new Divisa("CNY", "¥", "Chinese Renminbi", BigDecimal.ZERO, null),
                    new Divisa("JPY", "¥", "Japanese yen", BigDecimal.ZERO, null),
                    new Divisa("BRL", "R$", "Brazilian real", BigDecimal.ZERO, null),
                    new Divisa("INR", "₹", "Indian rupee", BigDecimal.ZERO, null)
            );

            divisaRepositorio.saveAll(divisasIniciales);
            log.info("Base de Datos Inicializada: Divisas básicas añadidas");

            divisaServicio.updateTipoCambio();
        }
    }

    public void inicializarDoctores() {
        if (doctorRepositorio.count() == 0) {
            Especialidad cardiologia = especialidadRepositorio.findByNombre("Cardiologia")
                    .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));

            Especialidad pediatria = especialidadRepositorio.findByNombre("Pediatria")
                    .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));

            Especialidad dermatologia = especialidadRepositorio.findByNombre("Dermatologia")
                    .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));

            Divisa MXN = divisaRepositorio.findById("MXN")
                    .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

            Divisa USD = divisaRepositorio.findById("USD")
                    .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

            List<Doctor> doctoresIniciales = List.of(
                    Doctor.builder()
                            .nombre("Miguel Angel").apellido("Hernandez Ruiz").email("hernandezruizmiguelangel526@gmail.com")
                            .contrasena("Miguel#2009").numeroTelefono("5578513424")
                            .rol(Rol.DOCTOR)
                            .precioLocal(new BigDecimal("245.00")).divisa(MXN)
                            .especialidades(new HashSet<>(Set.of(cardiologia)))
                            .build(),
                    Doctor.builder()
                            .nombre("Joshua").apellido("Jimenez Campuzano").email("jimenezcampuzanojo11@gmail.com")
                            .contrasena("Joshua#2009").numeroTelefono("5619350700")
                            .rol(Rol.DOCTOR)
                            .precioLocal(new BigDecimal("535.00")).divisa(USD)
                            .especialidades(new HashSet<>(Set.of(pediatria, dermatologia)))
                            .build()
            );

            doctorRepositorio.saveAll(doctoresIniciales);
            log.info("Base de Datos Inicializada: Doctores iniciadas");
        }
    }

    public void inicializarMedicinas() {
        if (medicinaRepositorio.count() == 0) {
            List<Medicina> medicinasIniciales = List.of(
                    new Medicina(null, "Paracetamol 500mg", "Laboratorios Pisa", "Paracetamol", 500, 50, "Tableta", LocalDate.of(2027, 6, 30)),
                    new Medicina(null, "Ibuprofeno 400mg", "Laboratorios Sophia", "Ibuprofeno", 300, 40, "Cápsula", LocalDate.of(2027, 3, 15)),
                    new Medicina(null, "Amoxicilina 500mg", "Laboratorios Pisa", "Amoxicilina trihidrato", 200, 30, "Cápsula", LocalDate.of(2026, 9, 1)),
                    new Medicina(null, "Metformina 850mg", "Laboratorios Liomont", "Clorhidrato de metformina", 400, 60, "Tableta", LocalDate.of(2027, 12, 31)),
                    new Medicina(null, "Losartán 50mg", "Laboratorios Pisa", "Losartán potásico", 250, 40, "Tableta", LocalDate.of(2027, 8, 15)),
                    new Medicina(null, "Omeprazol 20mg", "Laboratorios Sophia", "Omeprazol", 350, 50, "Cápsula", LocalDate.of(2027, 4, 30)),
                    new Medicina(null, "Atorvastatina 20mg", "Laboratorios Liomont", "Atorvastatina cálcica", 180, 25, "Tableta", LocalDate.of(2027, 10, 1)),
                    new Medicina(null, "Azitromicina 500mg", "Laboratorios Pisa", "Azitromicina dihidrato", 150, 20, "Tableta", LocalDate.of(2026, 11, 30)),
                    new Medicina(null, "Salbutamol 100mcg", "Laboratorios Sophia", "Sulfato de salbutamol", 100, 15, "Inhalador", LocalDate.of(2027, 2, 28)),
                    new Medicina(null, "Diclofenaco 100mg", "Laboratorios Liomont", "Diclofenaco sódico", 220, 35, "Supositorio", LocalDate.of(2027, 7, 15))
            );

            medicinaRepositorio.saveAll(medicinasIniciales);
            log.info("Base de Datos Inicializada: Medicinas básicas añadidas");
        }
    }
}
