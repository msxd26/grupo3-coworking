package com.grupo3.coworkingreservas.controllers.impl;


import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<?> createUsuario( @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDTO));
    }


    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

}

