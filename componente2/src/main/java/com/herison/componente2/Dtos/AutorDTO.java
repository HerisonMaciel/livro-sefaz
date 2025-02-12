package com.herison.componente2.Dtos;

import java.util.List;

public record AutorDTO(
        String id,
        String nome,
        List<LivroDTO> livros
) {}
