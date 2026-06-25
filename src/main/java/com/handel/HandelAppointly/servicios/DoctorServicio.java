package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.entidades.Horario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorServicio {
    DoctorRespuestaDto create(DoctorSolicitudDto solicitudDto);

    DoctorRespuestaDto findById(Long id);

    Page<DoctorRespuestaDto> findAll(Pageable paginable);

    Page<DoctorRespuestaDto> findByEspecialidad(String especialidad, Pageable paginable);

    List<Horario> findHorarios(Long id);

    DoctorRespuestaDto update(Long id, DoctorSolicitudDto solicitudDto);

    void delete(Long id);
}
