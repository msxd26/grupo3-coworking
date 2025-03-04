package com.grupo3.coworkingreservas.controllers;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.utils.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + "/v1/usuarios")
public interface UsuarioApi {
    @PostMapping
    ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @GetMapping("/{id}")
    ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios();

    @PutMapping("/{id}")
    ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarUsuario(@PathVariable Long id);
}