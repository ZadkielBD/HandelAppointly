package com.handel.HandelAppointly.dtos.solicitud;

import com.handel.HandelAppointly.dtos.respuesta.ConsultaMedicaRespuestaDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistorialMedicoSolicitudDto {
    private String paciente;
    private LocalDate fechaCreacion;
    private List<ConsultaMedicaRespuestaDto> consultas;
}
