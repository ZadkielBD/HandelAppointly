package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrador")
@EqualsAndHashCode(callSuper=true)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Administrador extends Usuario {

    @Column(unique = true ,nullable = false, name = "administrador_id")
    private Long administradorId;
}
