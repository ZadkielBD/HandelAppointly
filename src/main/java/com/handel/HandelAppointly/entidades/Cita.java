package com.handel.HandelAppointly.entidades;
import com.handel.HandelAppointly.enums.EstatusCita;
import com.handel.HandelAppointly.enums.TipoConsulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, name = "codigo")
    private String codigoCita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id",  nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id",  nullable = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ConsultaMedica consultaMedica;

    private String motivo;

    @Column(nullable = false, name = "estatus")
    @Enumerated(EnumType.STRING)
    private EstatusCita estatus = EstatusCita.PENDIENTE;

    @Column()
    @Enumerated(EnumType.STRING)
    private TipoConsulta consulta = TipoConsulta.GENERAL;

    @Column(nullable = false, name = "fecha_hora")
    private LocalDateTime fechaHora;
}
