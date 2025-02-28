package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Participante;
import com.grupo3.coworkingreservas.domain.dto.ParticipanteDTO;
import com.grupo3.coworkingreservas.repository.ParticipanteRepository;
import com.grupo3.coworkingreservas.service.ParticipanteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final ModelMapper modelMapper;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository, ModelMapper modelMapper) {
        this.participanteRepository = participanteRepository;
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
}