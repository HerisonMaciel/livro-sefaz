package com.herison.componente1.mapper;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.entity.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO toDTO(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getDescricao(),
                livro.getCategoria(),
                livro.getAutor().getId()
        );
    }

    public Livro toEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.id());
        livro.setTitulo(livroDTO.titulo());
        livro.setDescricao(livroDTO.descricao());
        livro.setCategoria(livroDTO.categoria());
        return livro;
    }
}
