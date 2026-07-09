package com.uca.pncparcialfinalhotel.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Aquí guardaremos la contraseña encriptada (Hash)

    @Enumerated(EnumType.STRING) // Guarda el nombre del rol (ej. "HUESPED") en la BD
    private Rol rol;

    // Relación: Un usuario puede tener muchas reservas
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;
}