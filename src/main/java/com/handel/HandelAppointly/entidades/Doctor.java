package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "doctores")
public class Doctor extends Usuario {

    @Column(unique = true ,nullable = false, name = "profesional_id")
    private String profesionalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @Column(nullable = false, name = "precio_cita")
    private BigDecimal precioLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_divisa", nullable = false)
    private Divisa divisa;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();
}
