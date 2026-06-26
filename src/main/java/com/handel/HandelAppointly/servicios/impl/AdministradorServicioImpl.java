package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import com.handel.HandelAppointly.entidades.Administrador;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.EmailDuplicadoException;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.AdministradorMapper;
import com.handel.HandelAppointly.repositorios.AdministradorRepositorio;
import com.handel.HandelAppointly.repositorios.UsuarioRepositorio;
import com.handel.HandelAppointly.servicios.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {
    private final AdministradorRepositorio administradorRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final AdministradorMapper administradorMapper;

    @Override
    @Transactional
    public AdministradorRespuestaDto create(AdministradorSolicitudDto solicitudDto) {
        if (usuarioRepositorio.findByEmail(solicitudDto.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("El email " + solicitudDto.getEmail() + " ya está registrado");
        }

        Administrador administrador = administradorMapper.aEntidad(solicitudDto);

        administrador.setRol(Rol.ADMINISTRADOR);

        administradorRepositorio.save(administrador);
        return administradorMapper.aRespuestaDto(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public AdministradorRespuestaDto findById(Long id) {
        Administrador administrador = findAdministradorById(id);

        return administradorMapper.aRespuestaDto(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdministradorRespuestaDto> findAll(Pageable paginable) {
        return administradorRepositorio.findAll(paginable)
                .map(administradorMapper::aRespuestaDto);
    }

    @Override
    @Transactional
    public AdministradorRespuestaDto update(Long id, AdministradorSolicitudDto solicitudDto) {
        Administrador administrador = findAdministradorById(id);

        administradorMapper.actualizarAdministrador(solicitudDto, administrador);

        administradorRepositorio.save(administrador);
        return administradorMapper.aRespuestaDto(administrador);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Administrador administrador = findAdministradorById(id);

        administradorRepositorio.delete(administrador);
    }

    private Administrador findAdministradorById(Long id) {
        return administradorRepositorio.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Administrador con id " + id + " no encontrado"));
    }
}
