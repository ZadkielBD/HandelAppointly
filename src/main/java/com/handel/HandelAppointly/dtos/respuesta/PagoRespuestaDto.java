package com.handel.HandelAppointly.dtos.respuesta;

import com.handel.HandelAppointly.enums.EstadoPago;
import com.handel.HandelAppointly.enums.MetodoPago;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagoRespuestaDto {
    private Long id;
    private Long citaId;
    private String codigoCita;
    private BigDecimal monto;
    private String divisa;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;
    private LocalDateTime fechaPago;
}