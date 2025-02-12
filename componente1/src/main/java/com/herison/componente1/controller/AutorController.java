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
public class AutorController implements AutorSwegger {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorDTO> criarAutor(@RequestBody AutorDTO autorDTO) {
        return ResponseEntity.status(201).body(autorService.criarAutor(autorDTO));
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        return ResponseEntity.ok(autorService.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable String id) {
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AutorDTO> buscarAutorPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(autorService.buscarAutorPorNome(nome));
    }

    @PostMapping("/atualizar")
    public ResponseEntity<?> atualizarAutor(@RequestBody AutorDTO autorDTO) {
        autorService.atualizarAutor(autorDTO.id(), autorDTO.nome());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutorPorId(@PathVariable String id) {
        autorService.deletarAutorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
