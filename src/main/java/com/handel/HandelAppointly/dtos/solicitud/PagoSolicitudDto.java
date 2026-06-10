package com.handel.HandelAppointly.dtos.solicitud;

import com.handel.HandelAppointly.enums.MetodoPago;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoSolicitudDto {
    @NotNull(message = "El ID de la cita es obligatorio")
    private Long citaId;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    @NotNull(message = "La divisa es obligatoria")
    private String codigoDivisa;
}
