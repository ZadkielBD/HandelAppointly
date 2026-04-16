package com.handel.HandelAppointly.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "divisas")
public class Divisa {
    @Id
    private String codigo;

    private String simbolo;
    private String nombre;

    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}
