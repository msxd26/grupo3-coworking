package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.domain.dto.SalaDTO;
import com.grupo3.coworkingreservas.exception.SalaNotFoundException;
import com.grupo3.coworkingreservas.repository.SalaRepository;
import com.grupo3.coworkingreservas.service.SalaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    public SalaServiceImpl(SalaRepository salaRepository, ModelMapper modelMapper) {
        this.salaRepository = salaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SalaDTO crearSala(SalaDTO salaDTO) {
        Sala sala = modelMapper.map(salaDTO, Sala.class);
        Sala salaGuardada = salaRepository.save(sala);
        return modelMapper.map(salaGuardada, SalaDTO.class);
    }

    @Override
    public Optional<SalaDTO> obtenerSalaPorId(Long id) {
        return salaRepository.findById(id)
                .map(sala -> modelMapper.map(sala, SalaDTO.class));
    }

    @Override
    public List<SalaDTO> obtenerTodasLasSalas() {
        List<Sala> salas = salaRepository.findAll();
        return salas.stream()
                .map(sala -> modelMapper.map(sala, SalaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SalaDTO> actualizarSala(Long id, SalaDTO salaDTO) {
        return salaRepository.findById(id)
                .map(sala -> {
                    modelMapper.map(salaDTO, sala);
                    Sala salaActualizada = salaRepository.save(sala);
                    return modelMapper.map(salaActualizada, SalaDTO.class);
                });
    }

    @Override
    public Optional<Void> eliminarSala(Long id) {
        return salaRepository.findById(id)
                .map(sala -> {
                    salaRepository.delete(sala);
                    return null; // Indica que la eliminación fue exitosa
                });
    }
}