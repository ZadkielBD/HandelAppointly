package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DivisaRespuestaDto {
    private String codigo;
    private String simbolo;
    private String nombre;
    private BigDecimal tipoCambio;
    private LocalDateTime ultimaActualizacion;
}