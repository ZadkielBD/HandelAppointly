package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "pacientes")
public class Paciente extends Usuario {

    @Column(unique = true ,nullable = false, name = "nacional_id")
    private String nacionalId;

    @Column(name = "contacto_emergencia_nombre")
    private String contactoEmergenciaNombre;

    @Column(name = "contacto_emergencia_no_telefono")
    private String contactoEmergenciaNoTelefono;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas =  new ArrayList<>();
}
