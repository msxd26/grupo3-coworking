package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

}
