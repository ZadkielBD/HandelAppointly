package com.handel.HandelAppointly.mappers;


import com.handel.HandelAppointly.dtos.respuesta.CitaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.CitaSolicitudDto;
import com.handel.HandelAppointly.entidades.Cita;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "consultaMedica", ignore = true)
    @Mapping(target = "codigoCita", ignore = true)
    @Mapping(target = "estatus", ignore = true)
    Cita aEntidad(CitaSolicitudDto solicitudDto);

    @Mapping(target = "paciente", source = "paciente.nombre")
    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "doctorNombre", source = "doctor.nombre")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "precioUSD", ignore = true)
    CitaRespuestaDto aRespuestaDto(Cita cita);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "consultaMedica", ignore = true)
    @Mapping(target = "codigoCita", ignore = true)
    @Mapping(target = "estatus", ignore = true)
    void actualizarEntidadDesdeDto(CitaSolicitudDto solicitudDto, @MappingTarget Cita cita);
}
