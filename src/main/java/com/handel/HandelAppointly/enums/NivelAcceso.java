package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NivelAcceso {
    SUPER_ADMIN("Super Administrador"),
    ADMIN("Administrador");

    private final String nombre;
}
