package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaMedicaRespuestaDto {
    private Long id;
    private String doctorNombre;
    private String codigoCita;
    private LocalDateTime fechaRegistro;
    private String diagnostico;
    private String sintomas;
    private String tratamiento;
    private RecetaMedicaRespuestaDto receta;
}
