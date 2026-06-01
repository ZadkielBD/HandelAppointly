package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdministradorServicio {
    AdministradorRespuestaDto create(AdministradorSolicitudDto solicitudDto);

    AdministradorRespuestaDto findById(Long id);

    Page<AdministradorRespuestaDto> findAll(Pageable paginable);

    AdministradorRespuestaDto update(Long id, AdministradorSolicitudDto solicitudDto);

    void delete(Long id);
}
