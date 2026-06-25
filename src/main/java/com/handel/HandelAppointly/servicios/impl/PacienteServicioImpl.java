package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.entidades.Paciente;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.EmailDuplicadoException;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.PacienteMapper;
import com.handel.HandelAppointly.entidades.HistorialMedico;
import com.handel.HandelAppointly.repositorios.CitaRepositorio;
import com.handel.HandelAppointly.repositorios.HistorialMedicoRepositorio;
import com.handel.HandelAppointly.repositorios.PacienteRepositorio;
import com.handel.HandelAppointly.repositorios.UsuarioRepositorio;
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
    private final CitaRepositorio citaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final HistorialMedicoRepositorio historialMedicoRepositorio;

    @Override
    @Transactional
    public PacienteRespuestaDto create(PacienteSolicitudDto solicitudDto) {
        if (usuarioRepositorio.findByEmail(solicitudDto.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("El email " + solicitudDto.getEmail() + " ya está registrado");
        }

        Paciente paciente = pacienteMapper.aEntidad(solicitudDto);
        paciente.setRol(Rol.PACIENTE);

        pacienteRepositorio.save(paciente);
        return pacienteMapper.aRespuestaDto(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteRespuestaDto findById(Long id) {
        Paciente paciente = findPacienteById(id);

        return pacienteMapper.aRespuestaDto(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PacienteRespuestaDto> findAll(Pageable paginable) {
        return pacienteRepositorio.findAll(paginable)
                .map(pacienteMapper::aRespuestaDto); // .map(p -> pacienteMapper.aRespuestaDto(p))
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PacienteRespuestaDto> findByDoctorId(Long doctorId, Pageable paginable) {
        return citaRepositorio.findPacientesByDoctorId(doctorId, paginable)
                .map(pacienteMapper::aRespuestaDto);
    }

    @Override
    @Transactional
    public PacienteRespuestaDto update(Long id, PacienteSolicitudDto solicitudDto) {
        Paciente paciente = findPacienteById(id);

        pacienteMapper.actualizarEntidadDesdeDto(solicitudDto, paciente);

        pacienteRepositorio.save(paciente);
        return pacienteMapper.aRespuestaDto(paciente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Paciente paciente = findPacienteById(id);

        pacienteRepositorio.delete(paciente);
    }

    private Paciente findPacienteById(Long id) {
        return pacienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Paciente con id " + id + " no encontrado"));
    }
}