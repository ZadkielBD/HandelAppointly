package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import com.handel.HandelAppointly.entidades.Administrador;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    Administrador aEntidad(AdministradorSolicitudDto solicitudDto);

    AdministradorRespuestaDto aRespuestaDto(Administrador administrador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "nivelAcceso", ignore = true)
    void actualizarAdministrador(AdministradorSolicitudDto solicitudDto, @MappingTarget Administrador administrador);

}
