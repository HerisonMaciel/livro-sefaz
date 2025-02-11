package com.herison.componente2.service;

import com.herison.componente2.Dtos.AutorDTO;
import com.herison.componente2.client.AutorClient;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorClient autorClient;

    public AutorService(AutorClient autorClient) {
        this.autorClient = autorClient;
    }

    public void buscarAutorPorId(String id) {
        //return autorClient.buscarAutorPorId(id);
    }

    public void deletarAutorPorId(String id) {
        //autorClient.deletarAutorPorId(id);
    }

    public void buscarAutorPorNome(String nome) {
        //return autorClient.buscarAutorPorNome(nome);
    }
}
