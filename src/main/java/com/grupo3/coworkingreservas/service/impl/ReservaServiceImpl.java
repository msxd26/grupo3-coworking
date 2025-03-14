package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;
import com.grupo3.coworkingreservas.domain.entities.Reserva;
import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.exception.ReservaNotFoundException;
import com.grupo3.coworkingreservas.exception.SalaNotFoundException;
import com.grupo3.coworkingreservas.repository.ReservaRepository;
import com.grupo3.coworkingreservas.repository.SalaRepository;
import com.grupo3.coworkingreservas.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        Sala sala = salaRepository.findById(reservaDTO.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        reserva.setSala(sala);
        Reserva reservaGuardada = reservaRepository.save(reserva);
        return modelMapper.map(reservaGuardada, ReservaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservaDTO> obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
        return Optional.of(modelMapper.map(reserva, ReservaDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ReservaDTO> actualizarReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Sala sala = salaRepository.findById(reservaDTO.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        modelMapper.map(reservaDTO, reserva);
        reserva.setSala(sala);
        Reserva reservaActualizada = reservaRepository.save(reserva);
        return Optional.of(modelMapper.map(reservaActualizada, ReservaDTO.class));
    }

    @Override
    @Transactional
    public void eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }



}