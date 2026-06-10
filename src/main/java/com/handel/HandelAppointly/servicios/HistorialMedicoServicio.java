package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.ConsultaMedicaRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.HistorialMedicoRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.ConsultaMedicaSolicitudDto;

public interface HistorialMedicoServicio {
    HistorialMedicoRespuestaDto findByPacienteId(Long pacienteId);
    ConsultaMedicaRespuestaDto llenarConsulta(Long consultaId, ConsultaMedicaSolicitudDto solicitudDto);
    ConsultaMedicaRespuestaDto actualizarConsulta(Long consultaId, ConsultaMedicaSolicitudDto solicitudDto);
}