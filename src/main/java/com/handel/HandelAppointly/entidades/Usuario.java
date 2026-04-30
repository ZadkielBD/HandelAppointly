package com.handel.HandelAppointly.entidades;

import com.handel.HandelAppointly.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true ,nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, name = "numero_telefono")
    private String numeroTelefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
