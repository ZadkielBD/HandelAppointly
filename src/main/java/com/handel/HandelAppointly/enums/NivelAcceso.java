package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NivelAcceso {
    DIRECCION_GENERAL("Direccion general"),
    DIRECCION_MEDICA("Direccion medica");

    private final String nombre;
}
