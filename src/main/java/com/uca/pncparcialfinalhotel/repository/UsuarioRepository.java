package com.uca.pncparcialfinalhotel.repository;

import com.uca.pncparcialfinalhotel.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Muy útil para el login y la seguridad de Spring Security
    Optional<Usuario> findByEmail(String email);
}