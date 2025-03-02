package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(Long id); // Método void, no devuelve nada
}


import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuario);

    UsuarioDTO findById(Long id);

    Boolean existsByEmail(String email);

}

