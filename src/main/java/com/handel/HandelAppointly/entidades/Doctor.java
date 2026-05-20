package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Table(name = "doctores")
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Usuario {

    @Column(nullable = false, name = "precio_cita")
    private BigDecimal precioLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_divisa", nullable = false)
    private Divisa divisa;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_especialidades",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private Set<Especialidad> especialidades = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();
}
