package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.MethodNotAllowedException;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.DoctorMapper;
import com.handel.HandelAppointly.repositorios.DivisaRepositorio;
import com.handel.HandelAppointly.repositorios.DoctorRepositorio;
import com.handel.HandelAppointly.repositorios.EspecialidadRepositorio;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class DoctorServicioImpl implements DoctorServicio {

    private final DoctorRepositorio doctorRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final DivisaRepositorio divisaRepositorio;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional
    public DoctorRespuestaDto create(DoctorSolicitudDto solicitudDto) {
        var especialidad = especialidadRepositorio.findByNombre(solicitudDto.especialidad())
                .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));

        var divisa = divisaRepositorio.findById(solicitudDto.codigoDivisa())
                .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

        Doctor doctor =  doctorMapper.aEntidad(solicitudDto);

        doctor.setEspecialidad(especialidad);
        doctor.setDivisa(divisa);
        doctor.setRol(Rol.DOCTOR);

        Doctor createdDoctor = doctorRepositorio.save(doctor);
        return doctorMapper.aRespuestaDto(createdDoctor);
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
    @Transactional
    public DoctorRespuestaDto update(Long id, DoctorSolicitudDto solicitudDto) {
        Doctor doctor = findDoctorById(id);

        doctorMapper.aEntidad(solicitudDto);

        Doctor updatedDoctor = doctorRepositorio.save(doctor);

        return doctorMapper.aRespuestaDto(updatedDoctor);
    }

    @Override
    @Transactional
    public DoctorRespuestaDto patch(Long id, DoctorSolicitudDto solicitudDto) {
        Doctor doctor = findDoctorById(id);

        ModelMapper patchMapper = new ModelMapper();
        patchMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        patchMapper.map(solicitudDto, doctor);

        if (solicitudDto.especialidad() != null) {
            var speciality = especialidadRepositorio.findByNombre(solicitudDto.especialidad())
                    .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));
            doctor.setEspecialidad(speciality);
        }

        if (solicitudDto.codigoDivisa() != null) {
            var currency = divisaRepositorio.findById(solicitudDto.codigoDivisa())
                    .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));
            doctor.setDivisa(currency);
        }

        Doctor patchedDoctor = doctorRepositorio.save(doctor);

        return doctorMapper.aRespuestaDto(patchedDoctor);
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
