package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.domain.entities.Role;
import com.grupo3.coworkingreservas.domain.entities.Usuario;
import com.grupo3.coworkingreservas.repository.RoleRepository;
import com.grupo3.coworkingreservas.repository.UsuarioRepository;
import com.grupo3.coworkingreservas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if(usuario == null){
            return null;
        }
        return convertToDTO(usuario);
    }


    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setRoles(new ArrayList<>(usuario.getRoles())); // Mapea las entidades Role directamente
        return usuarioDTO;
    }

    private Usuario convertToUsuario(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}
