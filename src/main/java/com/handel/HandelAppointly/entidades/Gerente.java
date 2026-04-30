package com.handel.HandelAppointly.entidades;

import com.handel.HandelAppointly.enums.NivelAcceso;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
@Table(name = "administrador")
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Gerente extends Usuario {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAcceso nivelAcceso;
}
