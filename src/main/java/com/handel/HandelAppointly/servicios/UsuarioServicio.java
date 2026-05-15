package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.entidades.Usuario;
import com.handel.HandelAppointly.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioServicio {

    UsuarioRespuestaDto getById(Long id);

    Page<UsuarioRespuestaDto> getAll(Rol rol, Pageable paginable);

    UsuarioSesionDto login(String email, String password);

//    Page<UserResponseDto> getAllByRol(Rol rol, Pageable paginable);

    void delete(Long id);
}
