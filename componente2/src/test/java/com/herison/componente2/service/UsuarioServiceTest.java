package com.herison.componente2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herison.componente2.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCriacaoDoUsuarioService() {
        assertNotNull(usuarioService);
    }

    @Test
    void testMockDePasswordEncoder() {
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCodificada");

        String senhaCodificada = passwordEncoder.encode("senha123");

        assertEquals("senhaCodificada", senhaCodificada);
        verify(passwordEncoder, times(1)).encode("senha123");
    }
}

