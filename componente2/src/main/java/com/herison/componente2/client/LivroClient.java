package com.herison.componente2.client;

import com.herison.componente2.Dtos.LivroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "componente1-livro-client",
        url = "${componente1.url}")
public interface LivroClient {

    @GetMapping("/api/v1/livros/{id}")
    LivroDTO buscarLivroPorId(@PathVariable Long id);

    @GetMapping("/api/v1/livros/titulo/{titulo}")
    LivroDTO buscarLivroPorTitulo(@PathVariable String titulo);

    @DeleteMapping("/api/v1/livros/{id}")
    void deletarLivroPorId(@PathVariable Long id);
}
