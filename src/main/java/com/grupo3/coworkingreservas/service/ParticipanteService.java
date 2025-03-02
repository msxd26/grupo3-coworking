package com.grupo3.coworkingreservas.service;

import com.grupo3.coworkingreservas.domain.dto.ParticipanteDTO;

import java.util.List;
import java.util.Optional;

public interface ParticipanteService {
    ParticipanteDTO crearParticipante(ParticipanteDTO participanteDTO);
    Optional<ParticipanteDTO> obtenerParticipantePorId(Long id);
    List<ParticipanteDTO> obtenerTodosLosParticipantes();
    Optional<ParticipanteDTO> actualizarParticipante(Long id, ParticipanteDTO participanteDTO);
    void eliminarParticipante(Long id);
    ParticipanteDTO agregarParticipanteASala(Long salaId, ParticipanteDTO pqrticipanteDTO);
    ParticipanteDTO agregarParticipanteAReserva(Long reservaId, ParticipanteDTO participanteDTO);
    void eliminarParticipanteDeSala(Long salaId, Long participanteId);
    void eliminarParticipanteDeReserva(Long reservaId, Long participanteId);
}