package com.grupo3.coworkingreservas.controllers.impl;

import com.grupo3.coworkingreservas.controllers.ReservaApi;
import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;
import com.grupo3.coworkingreservas.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservaController implements ReservaApi {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Override
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO creada = reservaService.crearReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Override
    public ResponseEntity<List<ReservaDTO>> obtenerTodasLasReservas() {
        List<ReservaDTO> reservas = reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }

    @Override
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Long id) {
        return reservaService.obtenerReservaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        return reservaService.actualizarReserva(id, reservaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Override
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}