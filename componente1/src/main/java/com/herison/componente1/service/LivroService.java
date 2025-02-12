package com.herison.componente1.service;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.entity.Livro;
import com.herison.componente1.mapper.LivroMapper;
import com.herison.componente1.repository.AutorRepository;
import com.herison.componente1.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LivroService {

    private final LivroRepository livroRepository;

    private final AutorRepository autorRepository;

    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.livroMapper = livroMapper;
    }

    public LivroDTO criarLivro(LivroDTO livroDTO) {
        Autor autor = autorRepository.findById(livroDTO.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Livro livro = new Livro();
        livro.setTitulo(livroDTO.titulo());
        livro.setDescricao(livroDTO.descricao());
        livro.setCategoria(livroDTO.categoria());
        livro.setAutor(autor);

        Livro savedLivro = livroRepository.save(livro);

        return new LivroDTO(
                savedLivro.getId(),
                savedLivro.getTitulo(),
                savedLivro.getDescricao(),
                savedLivro.getCategoria(),
                savedLivro.getAutor().getId()
        );
    }

    public List<LivroDTO> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LivroDTO buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
        return livroMapper.toDTO(livro);
    }

    public LivroDTO buscarLivroPorTitulo(String titulo) {
        Livro livro = livroRepository.findByTituloIgnoreCase(titulo)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o título: " + titulo));
        return livroMapper.toDTO(livro);
    }

    @Transactional
    public void atualizarLivro(Long id, LivroDTO livroDTO){
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }
        livroRepository.atualizarLivro(id, livroDTO.titulo(), livroDTO.descricao(), livroDTO.categoria());
    }

    public void deletarLivroPorId(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }
        livroRepository.deleteById(id);
    }

}
