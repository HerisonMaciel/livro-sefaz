package com.herison.componente2.client;

import com.herison.componente2.Dtos.AutorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "componente1-autor-client",
        url = "${componente1.url}")
public interface AutorClient {

    @GetMapping("/api/v1/autores/{id}")
    AutorDTO buscarAutorPorId(@PathVariable String id);

    @GetMapping("/api/v1/autores/nome/{nome}")
    AutorDTO buscarAutorPorNome(@PathVariable String nome);

    @DeleteMapping("/api/v1/autores/{id}")
    void deletarAutorPorId(@PathVariable String id);
}
