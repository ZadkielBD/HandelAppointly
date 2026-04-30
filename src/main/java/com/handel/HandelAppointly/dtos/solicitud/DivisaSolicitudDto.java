package com.handel.HandelAppointly.dtos.solicitud;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DivisaSolicitudDto {
    @NotBlank(message = "El código no puede estar en blanco")
    @Size(min = 3, max = 3, message = "El código debe tener 3 caracteres")
    private String codigo;

    @NotBlank(message = "El símbolo no se puede borrar")
    private String simbolo;

    @NotBlank(message = "El nombre no puede dejarse en blanco")
    private String nombre;

    private BigDecimal tipoCambio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ultimaActualizacion;
}
