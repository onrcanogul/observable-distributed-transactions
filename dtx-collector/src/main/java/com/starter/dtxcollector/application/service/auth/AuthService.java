package com.starter.dtxcollector.application.service.auth;

import com.starter.dtxcollector.application.dto.AuthResponseDto;
import com.starter.dtxcollector.application.service.jwt.JwtService;
import com.starter.dtxcollector.application.service.user.UserDetailService;
import com.starter.dtxcollector.domain.model.User;
import com.starter.dtxcollector.domain.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final UserDetailService userDetailService;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authtencationManager, UserDetailService userDetailService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authtencationManager;
        this.userDetailService = userDetailService;
        this.jwtService = jwtService;
    }

    public AuthResponseDto login(String username, String password) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Objects.requireNonNull(userDetails);
        String token = jwtService.generateToken(userDetails);
        return new AuthResponseDto(token, "TODO_REFRESH_TOKEN");
    }

    public void register(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Already Exist");
        }
        User user = User.create(username, email, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
