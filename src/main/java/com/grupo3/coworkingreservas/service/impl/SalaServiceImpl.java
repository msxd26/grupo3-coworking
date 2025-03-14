package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.domain.dto.SalaDTO;
import com.grupo3.coworkingreservas.repository.SalaRepository;
import com.grupo3.coworkingreservas.service.SalaService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SalaDTO crearSala(SalaDTO salaDTO) {
        Sala sala = modelMapper.map(salaDTO, Sala.class);
        Sala salaGuardada = salaRepository.save(sala);
        return modelMapper.map(salaGuardada, SalaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalaDTO> obtenerSalaPorId(Long id) {
        return salaRepository.findById(id)
                .map(sala -> modelMapper.map(sala, SalaDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalaDTO> obtenerTodasLasSalas() {
        List<Sala> salas = salaRepository.findAll();
        return salas.stream()
                .map(sala -> modelMapper.map(sala, SalaDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Optional<SalaDTO> actualizarSala(Long id, SalaDTO salaDTO) {
        return salaRepository.findById(id)
                .map(sala -> {
                    modelMapper.map(salaDTO, sala);
                    Sala salaActualizada = salaRepository.save(sala);
                    return modelMapper.map(salaActualizada, SalaDTO.class);
                });
    }

    @Override
    @Transactional
    public Optional<Void> eliminarSala(Long id) {
        return salaRepository.findById(id)
                .map(sala -> {
                    salaRepository.delete(sala);
                    return null; // Indica que la eliminación fue exitosa
                });
    }


}