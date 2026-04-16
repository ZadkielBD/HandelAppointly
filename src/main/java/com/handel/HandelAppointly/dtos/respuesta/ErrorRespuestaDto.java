package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorRespuestaDto(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        int estatus,
        String error,
        String mensaje,
        String path,
        Map<String,String> validationErrors
) { }
