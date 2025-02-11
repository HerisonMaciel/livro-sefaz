package com.herison.componente1.controller;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
@Tag(name = "API de Livros e Autores", description = "Endpoints para gerenciar autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    @Operation(summary = "Criar um novo autor", description = "Cria um novo autor no sistema")
    @ApiResponse(responseCode = "201", description = "Autor criado com sucesso")
    public ResponseEntity<AutorDTO> criarAutor(@RequestBody AutorDTO autorDTO) {
        return ResponseEntity.status(201).body(autorService.criarAutor(autorDTO));
    }

    @GetMapping
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores cadastrados")
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        return ResponseEntity.ok(autorService.listarAutores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor pelo ID", description = "Retorna os detalhes de um autor pelo ID")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable String id) {
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar autor pelo nome", description = "Retorna os detalhes de um autor pelo nome")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<AutorDTO> buscarAutorPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(autorService.buscarAutorPorNome(nome));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar autor pelo ID", description = "Remove um autor pelo ID")
    @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<Void> deletarAutorPorId(@PathVariable String id) {
        autorService.deletarAutorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
