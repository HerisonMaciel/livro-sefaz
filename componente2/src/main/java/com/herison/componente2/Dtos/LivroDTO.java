package com.herison.componente2.Dtos;

public record LivroDTO(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        String autorId
) {}
