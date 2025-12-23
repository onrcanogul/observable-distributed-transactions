package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.dto.AuthResponseDto;
import com.starter.dtxcollector.application.service.AuthService;
import com.starter.dtxcollector.presentation.dto.LoginModel;
import com.starter.dtxcollector.presentation.dto.RegisterModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(LoginModel model) {
        return ResponseEntity.ok(authService.login(model.username(), model.password()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(RegisterModel model) {
        authService.register(model.username(), model.email(), model.password());
        return ResponseEntity.status(200).build();
    }
}
