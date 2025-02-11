package com.herison.componente2.service;

import com.herison.componente2.Dtos.LivroDTO;
import com.herison.componente2.client.LivroClient;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroClient livroClient;

    public LivroService(LivroClient livroClient) {
        this.livroClient = livroClient;
    }

    public LivroDTO buscarLivroPorId(Long id) {
        return livroClient.buscarLivroPorId(id);
    }

    public void deletarLivroPorId(Long id) {
        livroClient.deletarLivroPorId(id);
    }

    public LivroDTO buscarLivroPorTitulo(String titulo) {
        return livroClient.buscarLivroPorTitulo(titulo);
    }

}
