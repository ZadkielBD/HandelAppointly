package com.handel.HandelAppointly.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "divisas")
public class Divisa {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 3)
    private String codigo;

    @Column(nullable = false, length = 3)
    private String simbolo;

    @Column(nullable = false, unique = true, length = 35)
    private String nombre;

    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}
