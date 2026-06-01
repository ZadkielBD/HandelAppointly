package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.CitaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.CitaSolicitudDto;
import com.handel.HandelAppointly.entidades.Cita;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.entidades.Paciente;
import com.handel.HandelAppointly.enums.EstatusCita;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.CitaMapper;
import com.handel.HandelAppointly.repositorios.CitaRepositorio;
import com.handel.HandelAppointly.repositorios.DoctorRepositorio;
import com.handel.HandelAppointly.repositorios.PacienteRepositorio;
import com.handel.HandelAppointly.servicios.CitaServicio;
import com.handel.HandelAppointly.utils.GeneradorCodigos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CitaServicioImpl implements CitaServicio {
    private final CitaRepositorio citaRepositorio;
    private final PacienteRepositorio pacienteRepositorio;
    private final DoctorRepositorio doctorRepositorio;
    private final CitaMapper citaMapper;

    @Override
    @Transactional
    public CitaRespuestaDto create(CitaSolicitudDto solicitudDto) {
        Paciente paciente = pacienteRepositorio.findById(solicitudDto.getPacienteId())
                .orElseThrow(() -> new ResourcesNotFoundException("Paciente no encontrado"));

        Doctor doctor = doctorRepositorio.findById(solicitudDto.getDoctorId())
                .orElseThrow(() -> new ResourcesNotFoundException("Doctor no encontrado"));

        Cita cita = citaMapper.aEntidad(solicitudDto);
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setEstatus(EstatusCita.PENDIENTE);
        cita.setCodigoCita(GeneradorCodigos.generarCodigoCita());

        return citaMapper.aRespuestaDto(citaRepositorio.save(cita));
    }

    @Override
    @Transactional(readOnly = true)
    public CitaRespuestaDto findById(Long id) {
        return citaMapper.aRespuestaDto(findCitaById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitaRespuestaDto> findAll(Pageable paginable) {
        return citaRepositorio.findAll(paginable)
                .map(citaMapper::aRespuestaDto);
    }

    @Override
    @Transactional
    public CitaRespuestaDto update(Long id, CitaSolicitudDto solicitudDto) {
        Cita cita = findCitaById(id);

        if (solicitudDto.getPacienteId() != null) {
            Paciente paciente = pacienteRepositorio.findById(solicitudDto.getPacienteId())
                    .orElseThrow(() -> new ResourcesNotFoundException("Paciente no encontrado"));
            cita.setPaciente(paciente);
        }

        if (solicitudDto.getDoctorId() != null) {
            Doctor doctor = doctorRepositorio.findById(solicitudDto.getDoctorId())
                    .orElseThrow(() -> new ResourcesNotFoundException("Doctor no encontrado"));
            cita.setDoctor(doctor);
        }

        citaMapper.actualizarEntidadDesdeDto(solicitudDto, cita);

        return citaMapper.aRespuestaDto(citaRepositorio.save(cita));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        citaRepositorio.delete(findCitaById(id));
    }

    private Cita findCitaById(Long id) {
        return citaRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Cita con id " + id + " no encontrada"));
    }
}