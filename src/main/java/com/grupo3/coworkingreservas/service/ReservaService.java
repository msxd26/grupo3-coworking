package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    ReservaDTO crearReserva(ReservaDTO reservaDTO);
    Optional<ReservaDTO> obtenerReservaPorId(Long id);
    List<ReservaDTO> obtenerTodasLasReservas();
    Optional<ReservaDTO> actualizarReserva(Long id, ReservaDTO reservaDTO);
    void eliminarReserva(Long id);
}