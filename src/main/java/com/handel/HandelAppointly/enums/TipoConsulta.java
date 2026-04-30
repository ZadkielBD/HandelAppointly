package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoConsulta {
    GENERAL("General"),
    URGENCIA("Urgencia"),
    SEGUIMIENTO("Seguimiento");

    private final String nombre;
}
