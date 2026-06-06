package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.HistorialMedicoRespuestaDto;
import com.handel.HandelAppointly.entidades.HistorialMedico;
import com.handel.HandelAppointly.mappers.HistorialMedicoMapper;
import com.handel.HandelAppointly.repositorios.HistorialMedicoRepositorio;
import com.handel.HandelAppointly.servicios.HistorialMedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistorialMedicoServicioImpl implements HistorialMedicoServicio {
    private final HistorialMedicoRepositorio historialMedicoRepositorio;
    private final HistorialMedicoMapper historialMedicoMapper;

    @Override
    @Transactional(readOnly = true)
    public HistorialMedicoRespuestaDto findByPacienteId(Long pacienteId) {
        HistorialMedico historial = historialMedicoRepositorio.findByPacienteId(pacienteId)
                .orElse(null); // puede no tener historial aún
        if (historial == null) return null;
        return historialMedicoMapper.aRespuestaDto(historial);
    }
}