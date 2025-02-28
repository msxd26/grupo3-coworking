package com.grupo3.coworkingreservas.controllers;

import com.grupo3.coworkingreservas.domain.dto.ParticipanteDTO;
import com.grupo3.coworkingreservas.utils.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + "/participantes")
public interface ParticipanteApi {
    @PostMapping
    ResponseEntity<ParticipanteDTO> crearParticipante(@RequestBody ParticipanteDTO participanteDTO);

    @GetMapping("/{id}")
    ResponseEntity<ParticipanteDTO> obtenerParticipantePorId(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<ParticipanteDTO>> obtenerTodosLosParticipantes();

    @PutMapping("/{id}")
    ResponseEntity<ParticipanteDTO> actualizarParticipante(@PathVariable Long id, @RequestBody ParticipanteDTO participanteDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarParticipante(@PathVariable Long id);
}