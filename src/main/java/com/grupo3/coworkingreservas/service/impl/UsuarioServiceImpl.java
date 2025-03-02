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
import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.domain.entities.Role;
import com.grupo3.coworkingreservas.domain.entities.Usuario;
import com.grupo3.coworkingreservas.repository.RoleRepository;
import com.grupo3.coworkingreservas.repository.UsuarioRepository;
import com.grupo3.coworkingreservas.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;


    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no encontrado"));

        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        if (usuarioDTO.isAdmin()) {
            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rol ROLE_ADMIN no encontrado"));
            roles.add(roleAdmin);
        }

        usuario.setRoles(roles);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertToDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO findById(Long id) {
        return convertToDTO(usuarioRepository.findById(id).orElseThrow());
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }




    private UsuarioDTO convertToDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario convertToUsuario(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}
