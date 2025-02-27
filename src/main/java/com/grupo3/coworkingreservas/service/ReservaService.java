package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO crearReserva(ReservaDTO reservaDTO);
    ReservaDTO obtenerReservaPorId(Long id);
    List<ReservaDTO> obtenerTodasLasReservas();
    ReservaDTO actualizarReserva(Long id, ReservaDTO reservaDTO);
    void eliminarReserva(Long id);
}