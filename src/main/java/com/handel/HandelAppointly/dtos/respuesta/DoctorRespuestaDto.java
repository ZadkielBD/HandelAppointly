package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoctorRespuestaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String numeroTelefono;
    private String especialidad;
    private BigDecimal precioLocal;
    private String codigoDivisa;
    private BigDecimal precioUSD; // Precio convertido a dolares
}
