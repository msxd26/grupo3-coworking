package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;

import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuario);

    UsuarioDTO findById(Long id);

    Boolean existsByEmail(String email);

}
