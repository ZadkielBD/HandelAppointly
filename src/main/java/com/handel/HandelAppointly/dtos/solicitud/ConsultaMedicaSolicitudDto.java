package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConsultaMedicaSolicitudDto {
    private Long citaId;

    @NotBlank(message = "El diagnóstico no puede estar en blanco")
    private String diagnostico;

    @NotBlank(message = "Los síntomas no pueden estar en blanco")
    private String sintomas;

    @NotBlank(message = "El tratamiento no puede estar en blanco")
    private String tratamiento;

    // Receta (opcional)
    private Long medicinaId;
    private String dosis;
    private Integer diasDuracion;
}