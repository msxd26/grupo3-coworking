package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.entities.Usuario;
import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.repository.UsuarioRepository;
import com.grupo3.coworkingreservas.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class); // Mapea DTO a entidad
        Usuario usuarioGuardado = (Usuario) usuarioRepository.save(usuario);
        return modelMapper.map(usuarioGuardado, UsuarioDTO.class); // Mapea entidad a DTO
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)); // Mapea entidad a DTO
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll(); // Obtiene la lista de entidades
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)) // Mapea cada entidad a DTO
                .collect(Collectors.toList()); // Convierte el stream a lista
    }

    @Override
    public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    modelMapper.map(usuarioDTO, usuario); // Actualiza la entidad con los datos del DTO
                    Usuario usuarioActualizado = (Usuario) usuarioRepository.save(usuario); // Guarda la entidad actualizada
                    return modelMapper.map(usuarioActualizado, UsuarioDTO.class); // Mapea la entidad actualizada a DTO
                });
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(usuarioRepository::delete); // Elimina el usuario si existe
    }
}