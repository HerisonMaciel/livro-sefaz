package com.herison.componente2.controller;

import com.herison.componente2.Dtos.LivroDTO;
import com.herison.componente2.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/livros")
@Tag(name = "API de Livros", description = "Endpoints para gerenciar livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro pelo ID", description = "Retorna os detalhes de um livro pelo ID")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    @Operation(summary = "Buscar livro pelo título", description = "Retorna os detalhes de um livro pelo título")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<LivroDTO> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro pelo ID", description = "Remove um livro pelo ID")
    @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
