package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Participante;
import com.grupo3.coworkingreservas.domain.dto.ParticipanteDTO;
import com.grupo3.coworkingreservas.domain.entities.Reserva;
import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.domain.entities.Usuario;
import com.grupo3.coworkingreservas.exception.ParticipanteNotFoundException;
import com.grupo3.coworkingreservas.exception.ReservaNotFoundException;
import com.grupo3.coworkingreservas.exception.SalaNotFoundException;
import com.grupo3.coworkingreservas.exception.UsuarioNotFoundException;
import com.grupo3.coworkingreservas.repository.ParticipanteRepository;
import com.grupo3.coworkingreservas.repository.ReservaRepository;
import com.grupo3.coworkingreservas.repository.SalaRepository;
import com.grupo3.coworkingreservas.repository.UsuarioRepository;
import com.grupo3.coworkingreservas.service.ParticipanteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    private final ModelMapper modelMapper;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository, SalaRepository salaRepository,
                                   ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.participanteRepository = participanteRepository;
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ParticipanteDTO crearParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = modelMapper.map(participanteDTO, Participante.class);
        Participante participanteGuardado = (Participante) participanteRepository.save(participante);
        return modelMapper.map(participanteGuardado, ParticipanteDTO.class);
    }

    @Override
    public Optional<ParticipanteDTO> obtenerParticipantePorId(Long id) {
        return participanteRepository.findById(id)
                .map(participante -> modelMapper.map(participante, ParticipanteDTO.class));
    }

    @Override
    public List<ParticipanteDTO> obtenerTodosLosParticipantes() {
        return (List<ParticipanteDTO>) participanteRepository.findAll().stream()
                .map(participante -> modelMapper.map(participante, ParticipanteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParticipanteDTO> actualizarParticipante(Long id, ParticipanteDTO participanteDTO) {
        return participanteRepository.findById(id)
                .map(participante -> {
                    modelMapper.map(participanteDTO, participante);
                    Participante participanteActualizado = (Participante) participanteRepository.saveAndFlush(participante);
                    return modelMapper.map(participanteActualizado, ParticipanteDTO.class);
                });
    }

    @Override
    public void eliminarParticipante(Long id) {
        participanteRepository.findById(id).ifPresent(participante -> {
            participanteRepository.delete(participante);
        });
    }



    @Override
    public ParticipanteDTO agregarParticipanteASala(Long salaId, ParticipanteDTO participanteDTO) {
        Sala sala = salaRepository.findById(salaId)
                .orElseThrow(() -> new SalaNotFoundException(salaId));
        Usuario usuario = usuarioRepository.findById(participanteDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException(participanteDTO.getUsuarioId()));
        Participante participante = modelMapper.map(participanteDTO, Participante.class);
        participante.setSala(sala);
        participante.setUsuario(usuario);
        Participante participanteGuardado = participanteRepository.save(participante);
        return modelMapper.map(participanteGuardado, ParticipanteDTO.class);
    }

    @Override
    public ParticipanteDTO agregarParticipanteAReserva(Long reservaId, ParticipanteDTO participanteDTO) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ReservaNotFoundException(reservaId));
        Usuario usuario = usuarioRepository.findById(participanteDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException(participanteDTO.getUsuarioId()));
        Participante participante = modelMapper.map(participanteDTO, Participante.class);
        participante.setReserva(reserva);
        participante.setUsuario(usuario);
        Participante participanteGuardado = participanteRepository.save(participante);
        return modelMapper.map(participanteGuardado, ParticipanteDTO.class);
    }

    @Override
    public void eliminarParticipanteDeSala(Long salaId, Long participanteId) {
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new ParticipanteNotFoundException(participanteId));
        if (!participante.getSala().getId().equals(salaId)) {
            throw new RuntimeException("El participante no pertenece a la sala especificada");
        }
        participanteRepository.delete(participante);
    }

    @Override
    public void eliminarParticipanteDeReserva(Long reservaId, Long participanteId) {
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new ParticipanteNotFoundException(participanteId));
        if (!participante.getReserva().getId().equals(reservaId)) {
            throw new RuntimeException("El participante no pertenece a la reserva especificada");
        }
        participanteRepository.delete(participante);
    }
}