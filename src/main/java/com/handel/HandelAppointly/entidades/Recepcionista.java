package com.handel.HandelAppointly.entidades;

import com.handel.HandelAppointly.enums.Turno;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "recepcionistas")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Recepcionista extends Usuario {
    @Column(name = "recepcionista_id")
    private String recepcionistaId;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @Column(name = "telefono")
    private String telefonoInterno;

}
