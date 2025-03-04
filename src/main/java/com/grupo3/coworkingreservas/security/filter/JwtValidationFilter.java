package com.grupo3.coworkingreservas.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.coworkingreservas.security.SimpleGrantedAuthorityJsonCreator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.grupo3.coworkingreservas.security.TokenJwtConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationFilter.class);

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class)
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
            enviarRespuestaDeError(response, HttpStatus.UNAUTHORIZED, "Token JWT expirado");
        } catch (JwtException e) {
            logger.error("Error al validar el token JWT: {}", e.getMessage());
            enviarRespuestaDeError(response, HttpStatus.UNAUTHORIZED, "Token JWT inválido");
        } catch (Exception e) {
            logger.error("Error inesperado al validar el token JWT: {}", e.getMessage());
            enviarRespuestaDeError(response, HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    private void enviarRespuestaDeError(HttpServletResponse response, HttpStatus status, String mensaje) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("error", mensaje);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(status.value());
        response.setContentType(CONTENT_TYPE);
    }
}