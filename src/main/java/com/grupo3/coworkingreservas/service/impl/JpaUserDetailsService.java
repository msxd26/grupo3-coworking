package com.grupo3.coworkingreservas.service.impl;

import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioDTO usuarioDTO = usuarioServiceImpl.findByEmail(email);

        if (usuarioDTO == null) {
            throw new UsernameNotFoundException(String.format("Usuario %s no existe en el sistema", email));
        }

        List<GrantedAuthority> authorities = usuarioDTO.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new User(usuarioDTO.getEmail(), usuarioDTO.getPassword(), authorities);
    }
}
