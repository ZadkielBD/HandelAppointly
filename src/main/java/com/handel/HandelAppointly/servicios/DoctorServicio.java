package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorServicio {

    DoctorRespuestaDto create(DoctorSolicitudDto requestDto);

    DoctorRespuestaDto findById(Long id);

    Page<DoctorRespuestaDto> findAll(Pageable pageable);

    DoctorRespuestaDto update(Long id, DoctorSolicitudDto requestDto);

    DoctorRespuestaDto patch(Long id, DoctorSolicitudDto requestDto);

    void delete(Long id);
}
