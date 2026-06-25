package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioServicio {
    UsuarioRespuestaDto findById(Long id);

    Page<UsuarioRespuestaDto> findAll(Rol rol, Pageable paginable);

    UsuarioSesionDto login(String email, String password);

    void delete(Long id);
}
