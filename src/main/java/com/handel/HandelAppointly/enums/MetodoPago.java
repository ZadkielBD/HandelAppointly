package com.handel.HandelAppointly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetodoPago {
    EFECTIVO("Efectivo"),
    TARJETA_CREDITO("Tarjeta de credito"),
    TARJETA_DEBITO("Tarjeta de debito"),
    TRANSFERENCIA_BANCARIA("Transferencia bancaria"),
    PAGO_EN_LINEA("Pago en linea"),
    MONEDEROS_DIGITALES("Monederos digitales");

    private final String nombre;
}
