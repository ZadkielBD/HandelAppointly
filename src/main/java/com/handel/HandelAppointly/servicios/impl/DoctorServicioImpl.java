package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.entidades.Especialidad;
import com.handel.HandelAppointly.entidades.Horario;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.EmailDuplicadoException;
import com.handel.HandelAppointly.excepciones.MethodNotAllowedException;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.DoctorMapper;
import com.handel.HandelAppointly.repositorios.*;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServicioImpl implements DoctorServicio {
    private final DoctorRepositorio doctorRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final DivisaRepositorio divisaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final DoctorMapper doctorMapper;
    private final HorarioRepositorio horarioRepositorio;

    @Override
    @Transactional
    public DoctorRespuestaDto create(DoctorSolicitudDto solicitudDto) {
        if (usuarioRepositorio.findByEmail(solicitudDto.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("El email " + solicitudDto.getEmail() + " ya está registrado");
        }

        List<Especialidad> especialidades = especialidadRepositorio.findAllById(solicitudDto.getEspecialidadesIds());

        var divisa = divisaRepositorio.findById(solicitudDto.getCodigoDivisa())
                .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

        Doctor doctor = doctorMapper.aEntidad(solicitudDto);

        doctor.setEspecialidades(new HashSet<>(especialidades));
        doctor.setDivisa(divisa);
        doctor.setRol(Rol.DOCTOR);

        doctorRepositorio.save(doctor);
        return doctorMapper.aRespuestaDto(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorRespuestaDto findById(Long id) {
        Doctor doctor = findDoctorById(id);

        return doctorMapper.aRespuestaDto(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorRespuestaDto> findAll(Pageable paginable) {
        return doctorRepositorio.findAll(paginable)
                .map(doctorMapper::aRespuestaDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorRespuestaDto> findByEspecialidad(String especialidad, Pageable paginable) {
        return doctorRepositorio.findByEspecialidadNombre(especialidad, paginable)
                .map(doctorMapper::aRespuestaDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Horario> findHorarios(Long id) {
        findDoctorById(id);
        return horarioRepositorio.findByDoctorIdOrderByDiaDeSemanaAscHoraInicioAsc(id);
    }

    @Override
    @Transactional
    public DoctorRespuestaDto update(Long id, DoctorSolicitudDto solicitudDto) {
        Doctor doctor = findDoctorById(id);

        doctorMapper.actualizarDoctor(solicitudDto, doctor);

        if (solicitudDto.getEspecialidadesIds() != null) {
            List<Especialidad> especialidades = especialidadRepositorio.findAllById(solicitudDto.getEspecialidadesIds());
            doctor.setEspecialidades(new HashSet<>(especialidades));
        }

        if (solicitudDto.getCodigoDivisa() != null) {
            var divisa = divisaRepositorio.findById(solicitudDto.getCodigoDivisa())
                    .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));
            doctor.setDivisa(divisa);
        }

        doctorRepositorio.save(doctor);
        return doctorMapper.aRespuestaDto(doctor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Doctor doctor = findDoctorById(id);

        if (!doctor.getCitas().isEmpty()) {
            throw new MethodNotAllowedException("No se puede eliminar a un médico que tenga citas programadas");
        }

        doctorRepositorio.delete(doctor);
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Doctor con id " + id + " no encontrado"));
    }
}
