package com.handel.HandelAppointly.dtos.respuesta;

import com.handel.HandelAppointly.enums.NivelAcceso;
import com.handel.HandelAppointly.enums.Rol;
import lombok.Data;

import java.util.Set;

@Data
public class GerenteRespuestaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String numeroTelefono;
    private Rol rol;

    private NivelAcceso nivelAcceso;
    private String clinicaNombre;

    private Set<DoctorRespuestaDto> doctores;
}
