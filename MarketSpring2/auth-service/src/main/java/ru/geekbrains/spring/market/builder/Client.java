package ru.geekbrains.spring.market.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.spring.market.api.AppError;
import ru.geekbrains.spring.market.api.JwtResponse;
import ru.geekbrains.spring.market.controllers.AuthControllers;
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

    public AuthControllers checkAuthClient (User user) {
        public ResponseEntity<?> createAuthToken(@RequestBody RegistrationUserDto registrationUserDto) {
            if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
                return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Вы не зарегистрированны, пароли не совпадают"), HttpStatus.BAD_REQUEST);
            }
            if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
                return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Вы не зарегистрированны, пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
            }
            Client user = new User();
            MimeMessage address = user.setEmail(registrationUserDto.getEmail());

            UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            return user.sendMail("hello ");
        }
    }
    public void sendMail(Session session) throws MessagingException {
        MimeMessage message = (new MimeMessageBuilder(session))
                .from("from@example.com")
                .to("to@example.com")
                .subject("hello")
                .build();

        Transport.send(message);
    }
}
