package com.grupo3.coworkingreservas.controllers.impl;

import com.grupo3.coworkingreservas.controllers.ParticipanteApi;
import com.grupo3.coworkingreservas.domain.dto.ParticipanteDTO;
import com.grupo3.coworkingreservas.exception.ParticipanteNotFoundException;
import com.grupo3.coworkingreservas.exception.ReservaNotFoundException;
import com.grupo3.coworkingreservas.exception.SalaNotFoundException;
import com.grupo3.coworkingreservas.exception.UsuarioNotFoundException;
import com.grupo3.coworkingreservas.service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParticipanteController implements ParticipanteApi {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @Override
    public ResponseEntity<ParticipanteDTO> crearParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        ParticipanteDTO creado = participanteService.crearParticipante(participanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Override
    public ResponseEntity<ParticipanteDTO> obtenerParticipantePorId(@PathVariable Long id) {
        return participanteService.obtenerParticipantePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ParticipanteDTO>> obtenerTodosLosParticipantes() {
        List<ParticipanteDTO> participantes = participanteService.obtenerTodosLosParticipantes();
        return ResponseEntity.ok(participantes);
    }

    @Override
    public ResponseEntity<ParticipanteDTO> actualizarParticipante(@PathVariable Long id, @RequestBody ParticipanteDTO participanteDTO) {
        return participanteService.actualizarParticipante(id, participanteDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Long id) {
         participanteService.eliminarParticipante(id);
                return ResponseEntity.noContent().build();

    }


    @Override
    public ResponseEntity<ParticipanteDTO> agregarParticipanteASala(@PathVariable Long salaId, @RequestBody ParticipanteDTO participanteDTO) {
        try {
            ParticipanteDTO agregado = participanteService.agregarParticipanteASala(salaId, participanteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(agregado);
        } catch (SalaNotFoundException | UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ParticipanteDTO> agregarParticipanteAReserva(@PathVariable Long reservaId, @RequestBody ParticipanteDTO participanteDTO) {
        try {
            ParticipanteDTO agregado = participanteService.agregarParticipanteAReserva(reservaId, participanteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(agregado);
        } catch (ReservaNotFoundException | UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> eliminarParticipanteDeSala(@PathVariable Long salaId, @PathVariable Long participanteId) throws ParticipanteNotFoundException, RuntimeException {
        participanteService.eliminarParticipanteDeSala(salaId, participanteId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> eliminarParticipanteDeReserva(@PathVariable Long reservaId, @PathVariable Long participanteId) throws ParticipanteNotFoundException, RuntimeException {
        participanteService.eliminarParticipanteDeReserva(reservaId, participanteId);
        return ResponseEntity.noContent().build();
    }



}