package com.herison.componente1.service;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.entity.Livro;
import com.herison.componente1.mapper.LivroMapper;
import com.herison.componente1.repository.LivroRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LivroService {

    private LivroRepository livroRepository;

    private LivroMapper livroMapper;

    public LivroDTO criarLivro(LivroDTO livroDTO) {
        Livro livro = livroMapper.toEntity(livroDTO);
        Livro savedLivro = livroRepository.save(livro);
        return livroMapper.toDTO(savedLivro);
    }

    public List<LivroDTO> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

}
