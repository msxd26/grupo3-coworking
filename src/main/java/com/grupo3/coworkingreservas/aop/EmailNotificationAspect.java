package com.grupo3.coworkingreservas.aop;


import com.grupo3.coworkingreservas.domain.dto.ReservaDTO;
import com.grupo3.coworkingreservas.domain.dto.UsuarioDTO;
import com.grupo3.coworkingreservas.service.impl.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class EmailNotificationAspect {


    private final EmailSenderService emailSenderService;

    @AfterReturning(value = "execution(* com.grupo3.coworkingreservas.service.UsuarioService.save(..))", returning = "usuarioDTO")
    public void sendRegistroUsuario(UsuarioDTO usuarioDTO) {
        String subject = "Bienvenido a Coworking Reservas";
        String body = "Hola " + usuarioDTO.getNombre() + ",\n\n" +
                "Tu cuenta ha sido creada exitosamente. Ahora puedes iniciar sesión en la plataforma.\n\n" +
                "Gracias por unirte a nosotros.";

        emailSenderService.sendEmail(usuarioDTO.getEmail(), subject, body);
        log.info("Correo de bienvenida enviado a {}", usuarioDTO.getEmail());
    }


    @AfterReturning(value = "execution(* com.grupo3.coworkingreservas.service.ReservaService.crearReserva(..))",
            returning = "reservaDTO")
    public void sendRegistroReserva(ReservaDTO reservaDTO) {
        UsuarioDTO usuarioDTO = reservaDTO.getUsuario();

        String subject = "Confirmación de Reserva";
        String body = "Hola " + usuarioDTO.getNombre() + ",\n\n" +
                "Tu reserva ha sido creada exitosamente. Aquí están los detalles:\n\n" +
                "Código de reserva: " + reservaDTO.getId() + "\n" +
                "Desde" + reservaDTO.getFechaInicio() + "\n" +
                "Hasta: " + reservaDTO.getFechaFin() + "\n\n" +
                "Gracias por reservar con nosotros.";

        emailSenderService.sendEmail(usuarioDTO.getEmail(), subject, body);
        log.info("Correo de confirmación de reserva enviado a {}", usuarioDTO.getEmail());
    }

}
