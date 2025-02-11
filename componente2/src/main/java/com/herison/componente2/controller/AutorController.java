package com.herison.componente2.controller;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/autores")
@Tag(name = "API de Autores", description = "Endpoints para gerenciar autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor pelo ID", description = "Retorna os detalhes de um autor pelo ID")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable String id) {
        autorService.buscarAutorPorId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar autor pelo nome", description = "Retorna os detalhes de um autor pelo nome")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<AutorDTO> buscarAutorPorNome(@PathVariable String nome) {
        autorService.buscarAutorPorNome(nome);
        return ResponseEntity.ok().build();
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
