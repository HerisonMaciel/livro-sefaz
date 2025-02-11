package com.herison.componente2.controller;

import com.herison.componente2.Dtos.LoginResponseDTO;
import com.herison.componente2.Dtos.RegistroUsuarioDTO;
import com.herison.componente2.Dtos.UsuarioDTO;
import com.herison.componente2.entidy.Usuario;
import com.herison.componente2.repository.UsuarioRepository;
import com.herison.componente2.security.TokenService;
import com.herison.componente2.service.AuthorizationService;
import com.herison.componente2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "API de Usuários", description = "Endpoints para gerenciar usuários")
public class UsuarioController {

    private final AuthenticationManager authenticationManager;

    private final AuthorizationService service;

    private final UsuarioRepository repository;

    private final TokenService tokenService;

    public UsuarioController(AuthenticationManager authenticationManager, AuthorizationService service, UsuarioRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistroUsuarioDTO data){
        if(this.service.loadUserByUsername(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
