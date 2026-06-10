package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.entidades.Medicina;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.repositorios.MedicinaRepositorio;
import com.handel.HandelAppointly.servicios.MedicinaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicinaServicioImpl implements MedicinaServicio {
    private final MedicinaRepositorio medicinaRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Medicina> findAll() {
        return medicinaRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Medicina findById(Long id) {
        return medicinaRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Medicina no encontrada"));
    }

    @Override
    @Transactional
    public Medicina create(Medicina medicina) {
        return medicinaRepositorio.save(medicina);
    }

    @Override
    @Transactional
    public Medicina update(Long id, Medicina medicina) {
        Medicina existente = findById(id);
        existente.setNombre(medicina.getNombre());
        existente.setFabricante(medicina.getFabricante());
        existente.setFormula(medicina.getFormula());
        existente.setExistencias(medicina.getExistencias());
        existente.setAlertaExistenciaMinima(medicina.getAlertaExistenciaMinima());
        existente.setUnidad(medicina.getUnidad());
        existente.setFechaExpiracion(medicina.getFechaExpiracion());
        return medicinaRepositorio.save(existente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        medicinaRepositorio.deleteById(id);
    }
}