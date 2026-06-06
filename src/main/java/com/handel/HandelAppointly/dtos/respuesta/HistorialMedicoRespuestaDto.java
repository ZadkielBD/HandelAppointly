package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistorialMedicoRespuestaDto {
    private Long id;
    private String paciente;
    private LocalDate fechaCreacion;
    private List<ConsultaMedicaRespuestaDto> consultas;
}
