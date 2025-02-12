package com.herison.componente2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herison.componente2.Dtos.LivroDTO;
import com.herison.componente2.client.LivroClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroClient livroClient; // Mock do FeignClient

    @InjectMocks
    private LivroService livroService; // Instância do service com mocks injetados

    private LivroDTO livroDTO;

    @BeforeEach
    void setUp() {
        livroDTO = new LivroDTO(1L, "O Hobbit", "Aventuras de Bilbo Bolseiro", "Fantasia", "123");
    }

    @Test
    void testCriarLivro() {
        when(livroClient.criarLivro(any(LivroDTO.class))).thenReturn(livroDTO);

        LivroDTO resultado = livroService.criarLivro(livroDTO);

        assertNotNull(resultado);
        assertEquals("O Hobbit", resultado.titulo());
        verify(livroClient, times(1)).criarLivro(any(LivroDTO.class));
    }

    @Test
    void testBuscarTodosLivros() {
        List<LivroDTO> livros = Arrays.asList(
                livroDTO,
                new LivroDTO(2L, "Senhor dos Anéis", "História de Frodo e o Anel", "Fantasia", "456")
        );

        when(livroClient.buscarTodosLivros()).thenReturn(livros);

        List<LivroDTO> resultado = livroService.buscarTodosLivros();

        assertEquals(2, resultado.size());
        verify(livroClient, times(1)).buscarTodosLivros();
    }

    @Test
    void testBuscarLivroPorId() {
        when(livroClient.buscarLivroPorId(1L)).thenReturn(livroDTO);

        LivroDTO resultado = livroService.buscarLivroPorId(1L);

        assertNotNull(resultado);
        assertEquals("O Hobbit", resultado.titulo());
        verify(livroClient, times(1)).buscarLivroPorId(1L);
    }

    @Test
    void testDeletarLivroPorId() {
        doNothing().when(livroClient).deletarLivroPorId(1L);

        livroService.deletarLivroPorId(1L);

        verify(livroClient, times(1)).deletarLivroPorId(1L);
    }

    @Test
    void testAtualizarLivro() {
        LivroDTO livroAtualizado = new LivroDTO(1L, "O Hobbit - Edição Especial", "Nova descrição", "Fantasia", "123");
        doNothing().when(livroClient).atualizarLivro(any(LivroDTO.class));

        livroService.atualizarLivro(1L, livroAtualizado);

        verify(livroClient, times(1)).atualizarLivro(any(LivroDTO.class));
    }

    @Test
    void testBuscarLivroPorTitulo() {
        when(livroClient.buscarLivroPorTitulo("O Hobbit")).thenReturn(livroDTO);

        LivroDTO resultado = livroService.buscarLivroPorTitulo("O Hobbit");

        assertNotNull(resultado);
        assertEquals("O Hobbit", resultado.titulo());
        verify(livroClient, times(1)).buscarLivroPorTitulo("O Hobbit");
    }
}

