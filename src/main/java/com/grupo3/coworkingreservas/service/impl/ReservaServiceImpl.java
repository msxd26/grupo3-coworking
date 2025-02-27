package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Reserva;
import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;
import com.grupo3.coworkingreservas.repository.ReservaRepository;
import com.grupo3.coworkingreservas.repository.SalaRepository;
import com.grupo3.coworkingreservas.service.ReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, SalaRepository salaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        reserva.setSala(sala);
        Reserva reservaGuardada = reservaRepository.save(reserva);
        return modelMapper.map(reservaGuardada, ReservaDTO.class);
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return modelMapper.map(reserva, ReservaDTO.class);
    }

    @Override
    public List<ReservaDTO> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO actualizarReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        modelMapper.map(reservaDTO, reserva);
        reserva.setSala(sala);
        Reserva reservaActualizada = reservaRepository.save(reserva);
        return modelMapper.map(reservaActualizada, ReservaDTO.class);
    }

    @Override
    public void eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }
}