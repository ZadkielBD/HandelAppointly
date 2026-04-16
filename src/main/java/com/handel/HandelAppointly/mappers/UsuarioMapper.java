package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.UsuarioSolicitudDto;
import com.handel.HandelAppointly.entidades.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper {

    UsuarioRespuestaDto usuarioToUsuarioRespuestaDto(Usuario usuario);

    Usuario usuarioSolicitudDtoToUsuario(UsuarioSolicitudDto usuarioSolicitudDto);
}
