package com.herison.componente2.controller;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.service.AutorService;
import feign.Body;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/autores")
@Tag(name = "API de Autores", description = "Endpoints para gerenciar autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    @Operation(summary = "Buscar autor pelo ID", description = "Retorna os detalhes de um autor pelo ID")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@RequestBody AutorDTO autorDTO) {
        return ResponseEntity.ok(autorService.criarAutor(autorDTO));
    }

    @GetMapping
    @Operation(summary = "Buscar autor pelo ID", description = "Retorna os detalhes de um autor pelo ID")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<List<AutorDTO>> buscarTodosAutores() {
        return ResponseEntity.ok(autorService.buscarTodosAutores());
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

    @PatchMapping("/{id}")
    @Operation(summary = "Edita autor pelo ID", description = "Edita um autor pelo ID")
    @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<?> atualizarAutor(@PathVariable String id, @RequestBody AutorDTO autorDTO) {
        autorService.atualizarAutor(id, autorDTO);
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
