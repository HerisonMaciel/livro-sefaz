package com.herison.componente1.mapper;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.entity.Livro;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AutorMapper {

    public AutorDTO toDTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNome(),
                autor.getLivros().stream()
                        .map(this::toLivroDTO)
                        .collect(Collectors.toList())
        );
    }

    public Autor toEntity(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setId(autorDTO.id());
        autor.setNome(autorDTO.nome());
        return autor;
    }

    private LivroDTO toLivroDTO(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getDescricao(),
                livro.getCategoria(),
                livro.getAutor().getId()
        );
    }

}
