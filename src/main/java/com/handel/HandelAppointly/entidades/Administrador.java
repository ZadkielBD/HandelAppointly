package com.handel.HandelAppointly.entidades;

import com.handel.HandelAppointly.enums.NivelAcceso;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id")
public class Administrador extends Usuario {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAcceso nivelAcceso;
}

