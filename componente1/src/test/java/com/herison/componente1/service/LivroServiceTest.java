package com.herison.componente1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.entity.Livro;
import com.herison.componente1.mapper.LivroMapper;
import com.herison.componente1.repository.AutorRepository;
import com.herison.componente1.repository.LivroRepository;
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
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private LivroMapper livroMapper;

    @InjectMocks
    private LivroService livroService;

    private Autor autor;
    private Livro livro;
    private LivroDTO livroDTO;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setId("1");
        autor.setNome("Machado de Assis");

        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setDescricao("Um dos maiores clássicos da literatura brasileira.");
        livro.setCategoria("Romance");
        livro.setAutor(autor);

        livroDTO = new LivroDTO(1L, "Dom Casmurro", "Um dos maiores clássicos da literatura brasileira.", "Romance", "1");
    }

    @Test
    void testCriarLivro() {
        when(autorRepository.findById("1")).thenReturn(Optional.of(autor));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        LivroDTO resultado = livroService.criarLivro(livroDTO);

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.titulo());
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void testCriarLivro_AutorNaoEncontrado() {
        when(autorRepository.findById("99")).thenReturn(Optional.empty());

        LivroDTO livroInvalido = new LivroDTO(2L, "Livro Desconhecido", "Sem descrição", "Fantasia", "99");

        Exception exception = assertThrows(RuntimeException.class, () -> livroService.criarLivro(livroInvalido));

        assertEquals("Autor não encontrado", exception.getMessage());
        verify(livroRepository, never()).save(any(Livro.class));
    }

    @Test
    void testListarLivros() {
        List<Livro> livros = Arrays.asList(livro);
        when(livroRepository.findAll()).thenReturn(livros);
        when(livroMapper.toDTO(any(Livro.class))).thenReturn(livroDTO);

        List<LivroDTO> resultado = livroService.listarLivros();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Dom Casmurro", resultado.get(0).titulo());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testBuscarLivroPorId() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);

        LivroDTO resultado = livroService.buscarLivroPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        verify(livroRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarLivroPorId_NaoEncontrado() {
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> livroService.buscarLivroPorId(99L));

        assertEquals("Livro não encontrado com o ID: 99", exception.getMessage());
        verify(livroRepository, times(1)).findById(99L);
    }

    @Test
    void testBuscarLivroPorTitulo() {
        when(livroRepository.findByTituloIgnoreCase("Dom Casmurro")).thenReturn(Optional.of(livro));
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);

        LivroDTO resultado = livroService.buscarLivroPorTitulo("Dom Casmurro");

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.titulo());
        verify(livroRepository, times(1)).findByTituloIgnoreCase("Dom Casmurro");
    }

    @Test
    void testBuscarLivroPorTitulo_NaoEncontrado() {
        when(livroRepository.findByTituloIgnoreCase("Livro Desconhecido")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> livroService.buscarLivroPorTitulo("Livro Desconhecido"));

        assertEquals("Livro não encontrado com o título: Livro Desconhecido", exception.getMessage());
        verify(livroRepository, times(1)).findByTituloIgnoreCase("Livro Desconhecido");
    }

    @Test
    @Transactional
    void testAtualizarLivro() {
        when(livroRepository.existsById(1L)).thenReturn(true);

        livroService.atualizarLivro(1L, livroDTO);

        verify(livroRepository, times(1)).atualizarLivro(1L, livroDTO.titulo(), livroDTO.descricao(), livroDTO.categoria());
    }

    @Test
    void testAtualizarLivro_NaoEncontrado() {
        when(livroRepository.existsById(99L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> livroService.atualizarLivro(99L, livroDTO));

        assertEquals("Livro não encontrado com o ID: 99", exception.getMessage());
        verify(livroRepository, never()).atualizarLivro(anyLong(), anyString(), anyString(), anyString());
    }

    @Test
    void testDeletarLivroPorId() {
        when(livroRepository.existsById(1L)).thenReturn(true);

        livroService.deletarLivroPorId(1L);

        verify(livroRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarLivroPorId_NaoEncontrado() {
        when(livroRepository.existsById(99L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> livroService.deletarLivroPorId(99L));

        assertEquals("Livro não encontrado com o ID: 99", exception.getMessage());
        verify(livroRepository, never()).deleteById(anyLong());
    }
}

