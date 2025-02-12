package com.herison.componente1.controller;

import com.herison.componente1.Dtos.LivroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "API de Livros", description = "Endpoints para gerenciar livros")
public interface LivroSwegger {

    @Operation(summary = "Criar livro pelo ID", description = "Retorna o detalhes de um livro criado")
    @ApiResponse(responseCode = "200", description = "Livro criado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não criado")
    ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO);

    @Operation(summary = "Buscar todos os livro", description = "Retorna os detalhes de todos os livro")
    @ApiResponse(responseCode = "200", description = "Livros encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livros não encontrado")
    ResponseEntity<List<LivroDTO>> listarLivros();

    @Operation(summary = "Buscar livro pelo ID", description = "Retorna os detalhes de um livro pelo ID")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id);

    @Operation(summary = "Buscar livro pelo título", description = "Retorna os detalhes de um livro pelo título")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    ResponseEntity<LivroDTO> buscarLivroPorTitulo(@PathVariable String titulo);

    @Operation(summary = "Editar livro pelo id", description = "Editar os detalhes de um livro pelo id")
    @ApiResponse(responseCode = "200", description = "Livro editado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    ResponseEntity<?> atualizarLivro(@RequestBody LivroDTO livroDTO);

    @Operation(summary = "Deletar livro pelo ID", description = "Remove um livro pelo ID")
    @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id);

}
