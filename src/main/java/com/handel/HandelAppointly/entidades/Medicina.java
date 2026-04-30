package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "medicinas")
public class Medicina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    private String formula;

    @Column(nullable = false)
    private Integer existencias;

    @Column(nullable = false, name = "alerta_existencia_minima")
    private Integer alertaExistenciaMinima;

    @Column(nullable = false)
    private String unidad;

    @Column(nullable = false, name = "fecha_expiracion")
    private LocalDate fechaExpiracion;
}
