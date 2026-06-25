package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.MedicinaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.MedicinaSolicitudDto;
import com.handel.HandelAppointly.entidades.Medicina;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.MedicinaMapper;
import com.handel.HandelAppointly.repositorios.MedicinaRepositorio;
import com.handel.HandelAppointly.servicios.MedicinaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicinaServicioImpl implements MedicinaServicio {
    private final MedicinaRepositorio medicinaRepositorio;
    private final MedicinaMapper medicinaMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<MedicinaRespuestaDto> findAll(Pageable pageable) {
        return medicinaRepositorio.findAll(pageable)
                .map(medicinaMapper::aRespuestaDto);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicinaRespuestaDto findById(Long id) {
        Medicina medicina = findMedicinaById(id);

        return medicinaMapper.aRespuestaDto(medicina);
    }

    @Override
    @Transactional
    public MedicinaRespuestaDto create(MedicinaSolicitudDto solicitudDto) {
        Medicina medicina = medicinaMapper.aEntidad(solicitudDto);

        medicinaRepositorio.save(medicina);
        return medicinaMapper.aRespuestaDto(medicina);
    }

    @Override
    @Transactional
    public MedicinaRespuestaDto update(Long id, MedicinaSolicitudDto solicitudDto) {
        Medicina medicina = findMedicinaById(id);

        medicinaMapper.actualizarMedicina(solicitudDto, medicina);

        medicinaRepositorio.save(medicina);
        return medicinaMapper.aRespuestaDto(medicina);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        medicinaRepositorio.deleteById(id);
    }

    private Medicina findMedicinaById(Long id) {
        return medicinaRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Medicina no encontrada"));
    }
}