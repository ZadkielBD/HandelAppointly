package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.entidades.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioRespuestaDto aRespuestaDto(Usuario usuario);

}
