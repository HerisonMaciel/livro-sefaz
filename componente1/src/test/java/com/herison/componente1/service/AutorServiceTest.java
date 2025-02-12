package com.herison.componente1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.mapper.AutorMapper;
import com.herison.componente1.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorMapper autorMapper;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;
    private AutorDTO autorDTO;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setId("1");
        autor.setNome("Machado de Assis");

        autorDTO = new AutorDTO("1", "Machado de Assis", null);
    }

    @Test
    void testCriarAutor() {
        when(autorMapper.toEntity(autorDTO)).thenReturn(autor);
        when(autorRepository.save(autor)).thenReturn(autor);
        when(autorMapper.toDTO(autor)).thenReturn(autorDTO);

        AutorDTO resultado = autorService.criarAutor(autorDTO);

        assertNotNull(resultado);
        assertEquals("Machado de Assis", resultado.nome());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    void testListarAutores() {
        List<Autor> autores = Arrays.asList(autor);
        when(autorRepository.findAll()).thenReturn(autores);
        when(autorMapper.toDTO(autor)).thenReturn(autorDTO);

        List<AutorDTO> resultado = autorService.listarAutores();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Machado de Assis", resultado.get(0).nome());
        verify(autorRepository, times(1)).findAll();
    }

    @Test
    void testBuscarAutorPorId() {
        when(autorRepository.findById("1")).thenReturn(Optional.of(autor));
        when(autorMapper.toDTO(autor)).thenReturn(autorDTO);

        AutorDTO resultado = autorService.buscarAutorPorId("1");

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        verify(autorRepository, times(1)).findById("1");
    }

    @Test
    void testBuscarAutorPorId_AutorNaoEncontrado() {
        when(autorRepository.findById("2")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> autorService.buscarAutorPorId("2"));

        assertEquals("Autor não encontrado com o ID: 2", exception.getMessage());
        verify(autorRepository, times(1)).findById("2");
    }

    @Test
    void testBuscarAutorPorNome() {
        when(autorRepository.findByNomeIgnoreCase("Machado de Assis")).thenReturn(Optional.of(autor));
        when(autorMapper.toDTO(autor)).thenReturn(autorDTO);

        AutorDTO resultado = autorService.buscarAutorPorNome("Machado de Assis");

        assertNotNull(resultado);
        assertEquals("Machado de Assis", resultado.nome());
        verify(autorRepository, times(1)).findByNomeIgnoreCase("Machado de Assis");
    }

    @Test
    void testBuscarAutorPorNome_AutorNaoEncontrado() {
        when(autorRepository.findByNomeIgnoreCase("Desconhecido")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> autorService.buscarAutorPorNome("Desconhecido"));

        assertEquals("Autor não encontrado com o nome: Desconhecido", exception.getMessage());
        verify(autorRepository, times(1)).findByNomeIgnoreCase("Desconhecido");
    }

    @Test
    @Transactional
    void testAtualizarAutor() {
        when(autorRepository.existsById("1")).thenReturn(true);

        autorService.atualizarAutor("1", "Novo Nome");

        verify(autorRepository, times(1)).atualizarAutor("1", "Novo Nome");
    }

    @Test
    void testAtualizarAutor_AutorNaoEncontrado() {
        when(autorRepository.existsById("2")).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> autorService.atualizarAutor("2", "Novo Nome"));

        assertEquals("Autor não encontrado com o ID: 2", exception.getMessage());
        verify(autorRepository, never()).atualizarAutor(anyString(), anyString());
    }

    @Test
    void testDeletarAutorPorId() {
        when(autorRepository.existsById("1")).thenReturn(true);

        autorService.deletarAutorPorId("1");

        verify(autorRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeletarAutorPorId_AutorNaoEncontrado() {
        when(autorRepository.existsById("2")).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> autorService.deletarAutorPorId("2"));

        assertEquals("Autor não encontrado com o ID: 2", exception.getMessage());
        verify(autorRepository, never()).deleteById(anyString());
    }
}

