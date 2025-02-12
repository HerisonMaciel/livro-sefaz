package com.herison.componente2.controller;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/autores")
public class AutorController implements AutorSwegger {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorDTO> criarAutor(@RequestBody AutorDTO autorDTO) {
        return ResponseEntity.ok(autorService.criarAutor(autorDTO));
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> buscarTodosAutores() {
        return ResponseEntity.ok(autorService.buscarTodosAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable String id) {
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AutorDTO> buscarAutorPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(autorService.buscarAutorPorNome(nome));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarAutor(@PathVariable String id, @RequestBody AutorDTO autorDTO) {
        autorService.atualizarAutor(id, autorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutorPorId(@PathVariable String id) {
        autorService.deletarAutorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
