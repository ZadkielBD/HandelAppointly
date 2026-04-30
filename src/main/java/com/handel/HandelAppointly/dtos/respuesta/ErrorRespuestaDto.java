package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorRespuestaDto (
        LocalDateTime timestamp,
        int estatus,
        String error,
        String mensaje,
        String path,
        Map<String,String> validationErrors
) { }
