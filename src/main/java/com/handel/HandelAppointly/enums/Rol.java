package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rol {
    USER("Defaul"),
    DOCTOR("Personal Medico"),
    ADMIN("Administrador"),
    PATIENT("Paciente");

    private final String description;
}