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

    @PostMapping("/salas/{salaId}")
    ResponseEntity<ParticipanteDTO> agregarParticipanteASala(@PathVariable Long salaId, @RequestBody ParticipanteDTO participanteDTO);

    @PostMapping("/reservas/{reservaId}")
    ResponseEntity<ParticipanteDTO> agregarParticipanteAReserva(@PathVariable Long reservaId, @RequestBody ParticipanteDTO participanteDTO);

    @DeleteMapping("/salas/{salaId}/participantes/{participanteId}")
    ResponseEntity<Void> eliminarParticipanteDeSala(@PathVariable Long salaId, @PathVariable Long participanteId);

    @DeleteMapping("/reservas/{reservaId}/participantes/{participanteId}")
    ResponseEntity<Void> eliminarParticipanteDeReserva(@PathVariable Long reservaId, @PathVariable Long participanteId);
}