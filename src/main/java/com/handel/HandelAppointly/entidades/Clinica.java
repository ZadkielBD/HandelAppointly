package com.handel.HandelAppointly.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "clinicas")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<Gerente> gerentes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<Recepcionista> recepcionistas =  new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinica_doctores",
            joinColumns = @JoinColumn(name = "clinica_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctores = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime fechaRegistro;
}