package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "historiales_medicos")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "paciente_id")
    private Paciente paciente;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;*/

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion;
}
