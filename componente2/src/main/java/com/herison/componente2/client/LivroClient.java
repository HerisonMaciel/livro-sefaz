package com.herison.componente2.client;

import com.herison.componente2.Dtos.LivroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "componente1-livro-client",
        url = "${componente1.url}")
public interface LivroClient {

    @PostMapping("/api/v1/livros")
    LivroDTO criarLivro(LivroDTO livroDTO);

    @GetMapping("/api/v1/livros")
    List<LivroDTO> buscarTodosLivros();

    @GetMapping("/api/v1/livros/{id}")
    LivroDTO buscarLivroPorId(@PathVariable Long id);

    @GetMapping("/api/v1/livros/titulo/{titulo}")
    LivroDTO buscarLivroPorTitulo(@PathVariable String titulo);

    @DeleteMapping("/api/v1/livros/{id}")
    void deletarLivroPorId(@PathVariable Long id);

    @PostMapping("/api/v1/livros/atualizar")
    void atualizarLivro(LivroDTO livroDTO);
}
