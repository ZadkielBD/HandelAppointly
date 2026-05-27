package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import com.handel.HandelAppointly.entidades.Administrador;
import com.handel.HandelAppointly.entidades.Doctor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", expression = "java(com.handel.HandelAppointly.enums.Rol.ADMINISTRADOR)")
    Administrador aEntidad(AdministradorSolicitudDto solicitudDto);

    AdministradorRespuestaDto aRespuestaDto(Administrador administrador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "nivelAcceso", ignore = true)
    void actualizarDoctorDesdeDto(AdministradorSolicitudDto solicitudDto, @MappingTarget Administrador administrador);

}
