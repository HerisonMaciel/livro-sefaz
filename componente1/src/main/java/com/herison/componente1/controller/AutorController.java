package com.herison.componente1.controller;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/autores")
@Tag(name = "API de Livros e Autores", description = "Endpoints para gerenciar autores")
public class AutorController {

    private AutorService autorService;

    // Endpoint para criar um autor
    @PostMapping("/autores")
    @Operation(summary = "Criar um novo autor", description = "Cria um novo autor no sistema")
    @ApiResponse(responseCode = "201", description = "Autor criado com sucesso")
    public ResponseEntity<AutorDTO> criarAutor(@RequestBody AutorDTO autorDTO) {
        return ResponseEntity.status(201).body(autorService.criarAutor(autorDTO));
    }

    // Endpoint para listar todos os autores
    @GetMapping("/autores")
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores cadastrados")
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        return ResponseEntity.ok(autorService.listarAutores());
    }

}
