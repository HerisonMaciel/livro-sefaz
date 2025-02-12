package com.herison.componente2.controller;

import com.herison.componente2.Dtos.LivroDTO;
import com.herison.componente2.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/livros")
public class LivroController implements LivroSwegger{

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(livroService.criarLivro(livroDTO));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodosLivros() {
        return ResponseEntity.ok(livroService.buscarTodosLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDTO> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        livroService.atualizarLivro(id, livroDTO);
        return ResponseEntity.ok("Livro atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
