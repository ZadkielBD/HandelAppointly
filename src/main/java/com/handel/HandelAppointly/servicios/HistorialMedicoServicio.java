package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.HistorialMedicoRespuestaDto;

public interface HistorialMedicoServicio {
    HistorialMedicoRespuestaDto findByPacienteId(Long pacienteId);
}
