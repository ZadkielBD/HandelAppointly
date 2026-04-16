package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@EqualsAndHashCode(callSuper=true)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Administrador extends Usuario {

    @Column(unique = true ,nullable = false, name = "empleado_id")
    private Long empleadoId;
}
