package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "horarios_doctores")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "dia_de_semana", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaDeSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_final",  nullable = false)
    private LocalTime horaFinal;

    @Column(name = "duracion_intervalo_minutos", nullable = false)
    private Integer duracionIntervaloMinutos;
}
