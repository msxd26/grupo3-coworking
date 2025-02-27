package com.grupo3.coworkingreservas.controllers;

import com.grupo3.coworkingreservas.domain.dto.SalaDTO;
import com.grupo3.coworkingreservas.utils.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + "/salas")
public interface SalaApi {

    @PostMapping
    ResponseEntity<SalaDTO> crearSala(@RequestBody SalaDTO salaDTO);

    @GetMapping
    ResponseEntity<List<SalaDTO>> obtenerTodasLasSalas();

    @GetMapping("/{id}")
    ResponseEntity<SalaDTO> obtenerSalaPorId(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<SalaDTO> actualizarSala(@PathVariable Long id, @RequestBody SalaDTO salaDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarSala(@PathVariable Long id);
}