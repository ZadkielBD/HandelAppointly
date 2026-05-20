package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.EspecialidadRespuestaDto;
import com.handel.HandelAppointly.repositorios.EspecialidadRepositorio;
import com.handel.HandelAppointly.servicios.EspecialidadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadImpl implements EspecialidadServicio {
    private final EspecialidadRepositorio especialidadRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadRespuestaDto> findAll() {
        return especialidadRepositorio.findAll()
                .stream()
                .map(e -> new EspecialidadRespuestaDto(e.getId(), e.getNombre(), e.getDescripcion()))
                .toList();
    }
}
