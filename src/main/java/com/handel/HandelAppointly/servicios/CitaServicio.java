package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.CitaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.CitaSolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CitaServicio {
    CitaRespuestaDto create(CitaSolicitudDto solicitudDto);

    CitaRespuestaDto findById(Long id);

    Page<CitaRespuestaDto> findAll(Pageable paginable);

    Page<CitaRespuestaDto> findByPacienteId(Long pacienteId, Pageable paginable);

    Page<CitaRespuestaDto> findByDoctorId(Long doctorId, Pageable paginable);

    List<CitaRespuestaDto> findCitasHoyByDoctorId(Long doctorId);

    CitaRespuestaDto update(Long id, CitaSolicitudDto solicitudDto);

    void delete(Long id);
}