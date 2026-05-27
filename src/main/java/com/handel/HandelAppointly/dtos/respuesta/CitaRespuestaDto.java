package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CitaRespuestaDto {
    private Long id;
    private String codigoCita;
    private String paciente;
    private String email;
    private String numeroTelefono;
    private String especialidad;
    private BigDecimal precioLocal;
    private String codigoDivisa;
    private BigDecimal precioUSD;
}
