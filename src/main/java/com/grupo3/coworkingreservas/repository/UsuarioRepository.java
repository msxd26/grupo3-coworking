package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}
