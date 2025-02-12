package com.herison.componente1.controller;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
@Tag(name = "API de Livros e Autores", description = "Endpoints para gerenciar livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(201).body(livroService.criarLivro(livroDTO));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDTO> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PostMapping("/atualizar")
    public ResponseEntity<?> atualizarLivro(@RequestBody LivroDTO livroDTO) {
        livroService.atualizarLivro(livroDTO.id(), livroDTO);
        return ResponseEntity.ok("Livro atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
