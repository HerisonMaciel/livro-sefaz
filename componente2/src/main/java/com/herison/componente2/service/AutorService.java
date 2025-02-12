package com.herison.componente2.service;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.client.AutorClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorClient autorClient;

    public AutorService(AutorClient autorClient) {
        this.autorClient = autorClient;
    }

    public AutorDTO criarAutor(AutorDTO autorDTO){
        return autorClient.criarAutor(autorDTO);
    }

    public List<AutorDTO> buscarTodosAutores(){
        return autorClient.buscarTodosAutores();
    }

    public AutorDTO buscarAutorPorId(String id) {
        return autorClient.buscarAutorPorId(id);
    }

    public void deletarAutorPorId(String id) {
        autorClient.deletarAutorPorId(id);
    }

    public void atualizarAutor(String id, AutorDTO autorDTO){
        AutorDTO autorRequest = new AutorDTO(id, autorDTO.nome(),  null);
        autorClient.atualizarAutor(autorRequest);
    }

    public AutorDTO buscarAutorPorNome(String nome) {
        return autorClient.buscarAutorPorNome(nome);
    }


}
