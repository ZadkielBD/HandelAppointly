package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.entidades.Paciente;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.PacienteMapper;
import com.handel.HandelAppointly.repositorios.PacienteRepositorio;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {
    private final PacienteRepositorio pacienteRepositorio;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional
    public PacienteRespuestaDto create(PacienteSolicitudDto solicitudDto) {
        Paciente paciente = pacienteMapper.aEntidad(solicitudDto);

        Paciente pacienteCreado = pacienteRepositorio.save(paciente);

        return pacienteMapper.aRespuestaDto(pacienteCreado);
    }

    @Override
    public PacienteRespuestaDto findById(Long id) {
        Paciente paciente = findPatientById(id);

        return pacienteMapper.aRespuestaDto(paciente);
    }

    @Override
    public Page<PacienteRespuestaDto> findAll(Pageable paginable) {
        return pacienteRepositorio.findAll(paginable)
                .map(pacienteMapper::aRespuestaDto);
    }

    @Override
    public PacienteRespuestaDto update(Long id, PacienteSolicitudDto solicitudDto) {
        Paciente paciente = findPatientById(id);

        pacienteMapper.actualizarEntidadDesdeDto(solicitudDto, paciente);

        Paciente pacienteActualizado = pacienteRepositorio.save(paciente);

        return pacienteMapper.aRespuestaDto(pacienteActualizado);
    }

    @Override
    public PacienteRespuestaDto patch(Long id, PacienteSolicitudDto solicitudDto) {
        Paciente paciente = findPatientById(id);

        pacienteMapper.actualizarEntidadDesdeDto(solicitudDto, paciente);

        Paciente pacienteActualizado = pacienteRepositorio.save(paciente);

        return pacienteMapper.aRespuestaDto(pacienteActualizado);
    }

    @Override
    public void delete(Long id) {
        Paciente paciente = findPatientById(id);

        pacienteRepositorio.delete(paciente);
    }

    private Paciente findPatientById(Long id) {
        return pacienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Paciente con id " + id + " no encontrado"));
    }
}
