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
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro no sistema")
    @ApiResponse(responseCode = "201", description = "Livro criado com sucesso")
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(201).body(livroService.criarLivro(livroDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
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

    @PostMapping("/atualizar")
    @Operation(summary = "Editar livro pelo id", description = "Editar os detalhes de um livro pelo id")
    @ApiResponse(responseCode = "200", description = "Livro editado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<?> atualizarLivro(@RequestBody LivroDTO livroDTO) {
        livroService.atualizarLivro(livroDTO.id(), livroDTO);
        return ResponseEntity.ok("Livro atualizado com sucesso!");
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
