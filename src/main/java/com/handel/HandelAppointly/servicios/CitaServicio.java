package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.CitaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.CitaSolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CitaServicio {
    CitaRespuestaDto create(CitaSolicitudDto solicitudDto);

    CitaRespuestaDto findById(Long id);

    Page<CitaRespuestaDto> findAll(Pageable paginable);

    CitaRespuestaDto update(Long id, CitaSolicitudDto solicitudDto);

    void delete(Long id);
}
