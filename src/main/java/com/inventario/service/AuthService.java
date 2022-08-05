package com.inventario.service;

import com.inventario.dto.RegisterRequest;
import com.inventario.entity.AppUser;
import com.inventario.entity.NotificationEmail;
import com.inventario.entity.VerificationToken;
import com.inventario.repository.AppUserRepository;
import com.inventario.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
    }

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        AppUser user = new AppUser();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        appUserRepository.save(user);
        String token = generateVerificationToken(user);
        /*
        mailService.sendMail(new NotificationEmail("Please activate account",
                user.getEmail(),"Thank you pls click: " + "http:localhost:8080/api/auth/accountVerification/" + token));
                */

    }

    private String generateVerificationToken(AppUser user) {
        String token = UUID.randomUUID().toString();
        VerificationToken veriVerificationToken =  new VerificationToken();

        veriVerificationToken.setToken(token);
        veriVerificationToken.setUser(user);

        verificationTokenRepository.save(veriVerificationToken);

        return token;
    }
}
