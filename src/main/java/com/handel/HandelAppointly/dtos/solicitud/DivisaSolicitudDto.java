package com.handel.HandelAppointly.dtos.solicitud;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DivisaSolicitudDto(
        @NotBlank(message = "El código no puede estar en blanco")
        @Size(min = 3, max = 3, message = "El código debe tener 3 caracteres")
        String codigo,
        @NotBlank(message = "El símbolo no se puede borrar")
        String simbolo,
        @NotBlank(message = "El nombre no puede dejarse en blanco")
        String nombre,

        BigDecimal tipoCambio,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime ultimaActualizacion
) { }
