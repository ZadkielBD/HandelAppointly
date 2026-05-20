package com.handel.HandelAppointly.dtos.externo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

//La respuesta de la api
public record FrankfurterRespuestaDto(
        BigDecimal cantidad,
        String base,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha,

        Map<String, BigDecimal> tasaCambio
) { }
