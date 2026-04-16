package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.MethodNotAllowedException;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
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
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public DoctorRespuestaDto create(DoctorSolicitudDto requestDto) {
        var especialidad = especialidadRepositorio.findByName(requestDto.especialidad())
                .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));

        var divisa = divisaRepositorio.findById(requestDto.codigoDivisa())
                .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

        Doctor doctor = modelMapper.map(requestDto, Doctor.class);

        doctor.setEspecialidad(especialidad);
        doctor.setDivisa(divisa);
        doctor.setRol(Rol.DOCTOR);

        Doctor createdDoctor = doctorRepositorio.save(doctor);
        return mapToResponse(createdDoctor);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorRespuestaDto findById(Long id) {
        Doctor doctor = findDoctorById(id);

        return mapToResponse(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorRespuestaDto> findAll(Pageable pageable) {
        return doctorRepositorio.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional
    public DoctorRespuestaDto update(Long id, DoctorSolicitudDto requestDto) {
        Doctor doctor = findDoctorById(id);

        modelMapper.map(requestDto, doctor);

        Doctor updatedDoctor = doctorRepositorio.save(doctor);

        return mapToResponse(updatedDoctor);
    }

    @Override
    @Transactional
    public DoctorRespuestaDto patch(Long id, DoctorSolicitudDto requestDto) {
        Doctor doctor = findDoctorById(id);

        ModelMapper patchMapper = new ModelMapper();
        patchMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        patchMapper.map(requestDto, doctor);

        if (requestDto.especialidad() != null) {
            var speciality = especialidadRepositorio.findByName(requestDto.especialidad())
                    .orElseThrow(() -> new ResourcesNotFoundException("Speciality not found"));
            doctor.setEspecialidad(speciality);
        }

        if (requestDto.codigoDivisa() != null) {
            var currency = divisaRepositorio.findById(requestDto.codigoDivisa())
                    .orElseThrow(() -> new ResourcesNotFoundException("Currency not found"));
            doctor.setDivisa(currency);
        }

        Doctor patchedDoctor = doctorRepositorio.save(doctor);

        return mapToResponse(patchedDoctor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Doctor doctor = findDoctorById(id);

        if (!doctor.getCitas().isEmpty()) {
            throw new MethodNotAllowedException("You cannot remove a doctor who has scheduled appointments");
        }

        doctorRepositorio.delete(doctor);
    }

    private DoctorRespuestaDto mapToResponse(Doctor doctor) {
        DoctorRespuestaDto responseDto = modelMapper.map(doctor, DoctorRespuestaDto.class);

        BigDecimal usdPrice = BigDecimal.ZERO;
        if (doctor.getDivisa() != null && doctor.getDivisa().getTipoCambio() != null) {
            if (doctor.getDivisa().getCodigo().equals("USD")) {
                usdPrice = doctor.getPrecioLocal();
            } else {
                usdPrice = doctor.getPrecioLocal().divide(
                        doctor.getDivisa().getTipoCambio(), 2, RoundingMode.HALF_UP);
            }
        }
        return new DoctorRespuestaDto(
                responseDto.id(),
                responseDto.profesionalId(),
                responseDto.nombre(),
                responseDto.apellido(),
                responseDto.email(),
                responseDto.numeroTelefono(),
                doctor.getEspecialidad().getNombre(),
                responseDto.precioLocal(),
                doctor.getDivisa().getCodigo(),
                usdPrice
        );
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Doctor with id " + id + " not found"));
    }
}
