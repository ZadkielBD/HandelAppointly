package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioServicio {

    UsuarioRespuestaDto getById(Long id);

    Page<UsuarioRespuestaDto> getAll(Rol rol, Pageable pageable);

//    Page<UserResponseDto> getAllByRole(Role role, Pageable pageable);

    void delete(Long id);
}
