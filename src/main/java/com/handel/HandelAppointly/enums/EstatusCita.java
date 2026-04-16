package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstatusCita {
    PENDIENTE("Pendiente", "#FFFB00"),
    CONFIRMADO("Confirmado", "#000FFF"),
    CANCELADO("Cancelado", "#FF0000"),
    COMPLETADO("Completado", "#04FF00");

    private final String descripcion;
    private final String codigo;

}
