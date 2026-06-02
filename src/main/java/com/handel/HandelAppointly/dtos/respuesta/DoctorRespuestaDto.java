package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DoctorRespuestaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String numeroTelefono;
    private List<String> especialidades;
    private BigDecimal precioLocal;
    private String codigoDivisa;
    private String simboloDivisa;
    private BigDecimal precioUSD;
}
