package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioServicio {

    UsuarioRespuestaDto getById(Long id);

    Page<UsuarioRespuestaDto> getAll(Rol rol, Pageable paginable);

//    Page<UserResponseDto> getAllByRol(Rol rol, Pageable paginable);

    void delete(Long id);
}
