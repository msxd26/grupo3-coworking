package com.grupo3.coworkingreservas.controllers.impl;

import com.grupo3.coworkingreservas.domain.dto.SalaDTO;
import com.grupo3.coworkingreservas.controllers.SalaApi;
import com.grupo3.coworkingreservas.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalaController implements SalaApi {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @Override
    public ResponseEntity<SalaDTO> crearSala(SalaDTO salaDTO) {
        SalaDTO creada = salaService.crearSala(salaDTO);
        return ResponseEntity.ok(creada);
    }

    @Override
    public ResponseEntity<List<SalaDTO>> obtenerTodasLasSalas() {
        List<SalaDTO> salas = salaService.obtenerTodasLasSalas();
        return ResponseEntity.ok(salas);
    }

    @Override
    public ResponseEntity<SalaDTO> obtenerSalaPorId(Long id) {
        return salaService.obtenerSalaPorId(id)
                .map(salaDTO -> ResponseEntity.ok(salaDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<SalaDTO> actualizarSala(Long id, SalaDTO salaDTO) {
        return salaService.actualizarSala(id, salaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> eliminarSala(Long id) {
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}