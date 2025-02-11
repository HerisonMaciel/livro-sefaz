package com.herison.componente1.Dtos;

import java.util.List;

public record AutorDTO(String id, String nome, List<LivroDTO> livros) {
}
