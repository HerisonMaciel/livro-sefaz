package com.herison.componente1.controller;

import com.herison.componente1.Dtos.LivroDTO;
import com.herison.componente1.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/livros")
@Tag(name = "API de Livros e Autores", description = "Endpoints para gerenciar livros")
public class LivroController {

    private LivroService livroService;

    @PostMapping("/livros")
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro no sistema")
    @ApiResponse(responseCode = "201", description = "Livro criado com sucesso")
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(201).body(livroService.criarLivro(livroDTO));
    }

    // Endpoint para listar todos os livros
    @GetMapping("/livros")
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }
}
