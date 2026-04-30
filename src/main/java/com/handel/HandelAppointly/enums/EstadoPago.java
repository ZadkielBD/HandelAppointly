package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPago {
    PENDIENTE("Pendiente"),
    COMPLETADO("Completado"),
    FALLIDO("Fallido"),
    REMBOLSADO("Rembolsado"),;

    private final String nombre;
}
