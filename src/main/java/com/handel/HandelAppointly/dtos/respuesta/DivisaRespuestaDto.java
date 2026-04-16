package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DivisaRespuestaDto(
        String codigo,
        String simbolo,
        String nombre,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        BigDecimal tipoCambio,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime ultimaActualizacion
) { }