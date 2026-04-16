package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public record DoctorRespuestaDto(
        Long id,
        String profesionalId,
        String nombre,
        String apellido,
        String email,
        String numeroTelefono,
        String especialidad,
        BigDecimal precioLocal,
        String codigoDivisa,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        BigDecimal precioUSD // Precio convertido a dolares
) {}
