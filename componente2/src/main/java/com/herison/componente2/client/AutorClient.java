package com.herison.componente2.client;

import com.herison.componente2.Dtos.AutorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "componente1-autor-client",
        url = "${componente1.url}")
public interface AutorClient {

    @PostMapping("/api/v1/autores")
    AutorDTO criarAutor(AutorDTO autorDTO);

    @GetMapping("/api/v1/autores")
    List<AutorDTO> buscarTodosAutores();

    @GetMapping("/api/v1/autores/{id}")
    AutorDTO buscarAutorPorId(@PathVariable String id);

    @GetMapping("/api/v1/autores/nome/{nome}")
    AutorDTO buscarAutorPorNome(@PathVariable String nome);

    @DeleteMapping("/api/v1/autores/{id}")
    void deletarAutorPorId(@PathVariable String id);

    @PostMapping("/api/v1/autores/atualizar")
    void atualizarAutor(AutorDTO autorDTO);

}
