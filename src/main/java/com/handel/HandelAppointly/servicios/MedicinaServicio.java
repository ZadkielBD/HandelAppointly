package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.MedicinaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.MedicinaSolicitudDto;
import com.handel.HandelAppointly.entidades.Medicina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicinaServicio {
    Page<MedicinaRespuestaDto> findAll(Pageable pageable);

    MedicinaRespuestaDto findById(Long id);

    MedicinaRespuestaDto create(MedicinaSolicitudDto solicitudDto);

    MedicinaRespuestaDto update(Long id, MedicinaSolicitudDto solicitudDto);

    void delete(Long id);
}