package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rol {
    DOCTOR("Personal Medico"),
    GERENTE("Gerente"),
    PACIENTE("Paciente"),
    RECEPCIONISTA("Recepcionista"),;

    private final String descripcion;
}