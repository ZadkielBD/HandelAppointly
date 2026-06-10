package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.EspecialidadRespuestaDto;
import com.handel.HandelAppointly.entidades.Especialidad;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.repositorios.EspecialidadRepositorio;
import com.handel.HandelAppointly.servicios.EspecialidadServicio;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional(readOnly = true)
    public EspecialidadRespuestaDto findById(Long id) {
        Especialidad e = especialidadRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));
        return new EspecialidadRespuestaDto(e.getId(), e.getNombre(), e.getDescripcion());
    }

    @Override
    @Transactional
    public EspecialidadRespuestaDto create(String nombre, String descripcion) {
        Especialidad e = Especialidad.builder().nombre(nombre).descripcion(descripcion).build();
        especialidadRepositorio.save(e);
        return new EspecialidadRespuestaDto(e.getId(), e.getNombre(), e.getDescripcion());
    }

    @Override
    @Transactional
    public EspecialidadRespuestaDto update(Long id, String nombre, String descripcion) {
        Especialidad e = especialidadRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Especialidad no encontrada"));
        e.setNombre(nombre);
        e.setDescripcion(descripcion);
        especialidadRepositorio.save(e);
        return new EspecialidadRespuestaDto(e.getId(), e.getNombre(), e.getDescripcion());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        especialidadRepositorio.deleteById(id);
    }
}
