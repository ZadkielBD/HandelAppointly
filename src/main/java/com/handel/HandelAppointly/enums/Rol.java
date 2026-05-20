package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rol {
    DOCTOR("Personal Medico"),
    ADMINISTRADOR("Administrador"),
    PACIENTE("Paciente"),;

    private final String descripcion;
}