package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EspecialidadSolicitudDto {
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    private String descripcion;
}
