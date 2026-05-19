package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Paciente extends Usuario {

    @Column(nullable = false, name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "contacto_emergencia_nombre")
    private String contactoEmergenciaNombre;

    @Column(name = "contacto_emergencia_telefono")
    private String contactoEmergenciaTelefono;

    @Builder.Default
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cita> citas =  new ArrayList<>();
}
