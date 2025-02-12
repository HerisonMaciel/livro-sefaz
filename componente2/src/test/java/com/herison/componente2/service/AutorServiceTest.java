package com.herison.componente2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.client.AutorClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @Mock
    private AutorClient autorClient;

    @InjectMocks
    private AutorService autorService;

    private AutorDTO autorDTO;

    @BeforeEach
    void setUp() {
        autorDTO = new AutorDTO("1", "M.H. Bezerra", null);
    }

    @Test
    void testCriarAutor() {
        when(autorClient.criarAutor(any(AutorDTO.class))).thenReturn(autorDTO);

        AutorDTO resultado = autorService.criarAutor(autorDTO);

        assertNotNull(resultado);
        assertEquals("M.H. Bezerra", resultado.nome());
        verify(autorClient, times(1)).criarAutor(any(AutorDTO.class));
    }

    @Test
    void testBuscarTodosAutores() {
        List<AutorDTO> autores = Arrays.asList(autorDTO, new AutorDTO("2", "George R.R. Martin", null));
        when(autorClient.buscarTodosAutores()).thenReturn(autores);

        List<AutorDTO> resultado = autorService.buscarTodosAutores();

        assertEquals(2, resultado.size());
        verify(autorClient, times(1)).buscarTodosAutores();
    }

    @Test
    void testBuscarAutorPorId() {
        when(autorClient.buscarAutorPorId("1")).thenReturn(autorDTO);

        AutorDTO resultado = autorService.buscarAutorPorId("1");

        assertNotNull(resultado);
        assertEquals("M.H. Bezerra", resultado.nome());
        verify(autorClient, times(1)).buscarAutorPorId("1");
    }

    @Test
    void testDeletarAutorPorId() {
        doNothing().when(autorClient).deletarAutorPorId("1");

        autorService.deletarAutorPorId("1");

        verify(autorClient, times(1)).deletarAutorPorId("1");
    }

    @Test
    void testAtualizarAutor() {
        AutorDTO autorAtualizado = new AutorDTO("1", "Bezerra Atualizado", null);
        doNothing().when(autorClient).atualizarAutor(any(AutorDTO.class));

        autorService.atualizarAutor("1", autorAtualizado);

        verify(autorClient, times(1)).atualizarAutor(any(AutorDTO.class));
    }

    @Test
    void testBuscarAutorPorNome() {
        when(autorClient.buscarAutorPorNome("M.H. Bezerra")).thenReturn(autorDTO);

        AutorDTO resultado = autorService.buscarAutorPorNome("M.H. Bezerra");

        assertNotNull(resultado);
        assertEquals("M.H. Bezerra", resultado.nome());
        verify(autorClient, times(1)).buscarAutorPorNome("M.H. Bezerra");
    }
}

