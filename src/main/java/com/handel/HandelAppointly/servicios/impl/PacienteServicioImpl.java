package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.entidades.Paciente;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.repositorios.PacienteRepositorio;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {
    private final PacienteRepositorio pacienteRepositorio;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PacienteRespuestaDto create(PacienteSolicitudDto solicitudDto) {
        Paciente paciente = modelMapper.map(solicitudDto, Paciente.class);

        Paciente createdPaciente = pacienteRepositorio.save(paciente);

        return modelMapper.map(createdPaciente, PacienteRespuestaDto.class);
    }

    @Override
    public PacienteRespuestaDto findById(Long id) {
        Paciente paciente = findPatientById(id);

        return modelMapper.map(paciente, PacienteRespuestaDto.class);
    }

    @Override
    public Page<PacienteRespuestaDto> findAll(Pageable paginable) {
        return pacienteRepositorio.findAll(paginable)
                .map(p -> modelMapper.map(p, PacienteRespuestaDto.class));
    }

    @Override
    public PacienteRespuestaDto update(Long id, PacienteSolicitudDto solicitudDto) {
        Paciente paciente = findPatientById(id);

        modelMapper.map(solicitudDto, paciente);

        Paciente updatedPaciente = pacienteRepositorio.save(paciente);

        return modelMapper.map(updatedPaciente, PacienteRespuestaDto.class);
    }

    @Override
    public PacienteRespuestaDto patch(Long id, PacienteSolicitudDto solicitudDto) {
        Paciente paciente = findPatientById(id);

        ModelMapper patchMapper = new ModelMapper();
        patchMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        patchMapper.map(solicitudDto, paciente);

        Paciente patchedPaciente = pacienteRepositorio.save(paciente);

        return modelMapper.map(patchedPaciente, PacienteRespuestaDto.class);
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
