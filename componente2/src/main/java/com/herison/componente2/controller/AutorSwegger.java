package com.herison.componente2.controller;

import com.herison.componente2.Dtos.AutorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Tag(name = "API de Autores", description = "Endpoints para gerenciar autores")
public interface AutorSwegger {


    @Operation(summary = "Criar Autor", description = "Retorna os detalhes de um autor criado")
    @ApiResponse(responseCode = "200", description = "Autor criado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não criado")
    ResponseEntity<AutorDTO> criarAutor(AutorDTO autorDTO);

    @Operation(summary = "Buscar todos autores", description = "Retorna os detalhes de todos autores")
    @ApiResponse(responseCode = "200", description = "Autores encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autores não encontrados")
    ResponseEntity<List<AutorDTO>> buscarTodosAutores();

    @Operation(summary = "Buscar autor pelo ID", description = "Retorna os detalhes de um autor pelo ID")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable String id);

    @Operation(summary = "Buscar autor pelo nome", description = "Retorna os detalhes de um autor pelo nome")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    ResponseEntity<AutorDTO> buscarAutorPorNome(@PathVariable String nome);

    @Operation(summary = "Atualizar autor pelo ID", description = "atualiza um autor pelo ID")
    @ApiResponse(responseCode = "204", description = "Autor atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    ResponseEntity<?> atualizarAutor(@PathVariable String id, @RequestBody AutorDTO autorDTO);

    @Operation(summary = "Remover autor pelo ID", description = "Remove um autor pelo ID")
    @ApiResponse(responseCode = "204", description = "Autor removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    ResponseEntity<Void> deletarAutorPorId(@PathVariable String id);

}
