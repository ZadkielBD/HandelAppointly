package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorServicio {

    DoctorRespuestaDto create(DoctorSolicitudDto solicitudDto);

    DoctorRespuestaDto findById(Long id);

    Page<DoctorRespuestaDto> findAll(Pageable paginable);

    DoctorRespuestaDto update(Long id, DoctorSolicitudDto solicitudDto);

    void delete(Long id);
}
