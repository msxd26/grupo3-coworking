package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.SalaDTO;

import java.util.List;
import java.util.Optional;

public interface SalaService {
    SalaDTO crearSala(SalaDTO salaDTO);
    Optional<SalaDTO> obtenerSalaPorId(Long id);
    List<SalaDTO> obtenerTodasLasSalas();
   Optional <SalaDTO> actualizarSala(Long id, SalaDTO salaDTO);
    Optional <Void> eliminarSala(Long id);
}