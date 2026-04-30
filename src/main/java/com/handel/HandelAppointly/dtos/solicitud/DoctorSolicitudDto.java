package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class DoctorSolicitudDto{
    @NotBlank(message = "Doctor no puede estar en blanco")
    @Pattern(regexp = "^[0-9]{7,8}$")
    private String profesionalId; //Matricula, la cual debe de seguir un patron: 87648390

    @NotBlank(message = "El nombre no puede dejarse en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede dejarse en blanco")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede quedar en blanco")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres y un máximo de 20", max = 20)
    private String contrasena;

    @NotBlank(message = "El número de teléfono no puede dejarse en blanco")
    private String numeroTelefono;

    @NotBlank(message = "Especialidad no puede estar en blanco")
    private Set<Long> especialidadesIds;

    @NotNull(message = "Precio local no puede estar en blanco")
    private BigDecimal precioLocal;

    @NotBlank(message = "Codigo divisa no puede estar en blanco")
    private String codigoDivisa;
}
