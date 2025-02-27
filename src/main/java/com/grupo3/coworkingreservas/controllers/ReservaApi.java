package com.grupo3.coworkingreservas.controllers;

import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;
import com.grupo3.coworkingreservas.utils.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + "/reservas")
public interface ReservaApi {

    @PostMapping
    ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO);

    @GetMapping
    ResponseEntity<List<ReservaDTO>> obtenerTodasLasReservas();

    @GetMapping("/{id}")
    ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarReserva(@PathVariable Long id);
}