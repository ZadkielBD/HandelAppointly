package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "recetas_medicas")
public class RecetaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cusulta_medica_id", nullable = false, unique = true)
    private ConsultaMedica consultaMedica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicina_id")
    private Medicina medicinaId;

    @Column(columnDefinition = "TEXT")
    private String dosis;

    @Column(name = "dias_duracion", nullable = false)
    private Integer diasDuracion;
}
