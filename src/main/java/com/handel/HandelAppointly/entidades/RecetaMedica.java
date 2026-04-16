package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "recetas_medicas")
public class RecetaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private HistorialMedico historialMedico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicina medicina;

    @Column(columnDefinition = "TEXT")
    private String dosis;

    @Column(nullable = false)
    private Integer diasDuracion;
}
