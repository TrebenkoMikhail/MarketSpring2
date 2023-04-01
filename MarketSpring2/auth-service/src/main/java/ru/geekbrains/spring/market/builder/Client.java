package ru.geekbrains.spring.market.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.entities.User;
import ru.geekbrains.spring.market.services.UserService;
import ru.geekbrains.spring.market.utils.JwtTokenUtil;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
@Component
@RequiredArgsConstructor
public class Client extends User {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public void sendMail(Session session) throws MessagingException {
        MimeMessage message = (new MimeMessageBuilder(session))
                .from("from@example.com")
                .to("to@example.com")
                .subject("hello")
                .build();

        Transport.send(message);
    }
}
