package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;


public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuario);

    UsuarioDTO findById(Long id);

    Boolean existsByEmail(String email);

    UsuarioDTO findByEmail(String email);

}

