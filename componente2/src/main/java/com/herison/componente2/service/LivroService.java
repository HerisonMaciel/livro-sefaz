package com.herison.componente2.service;

import com.herison.componente2.Dtos.LivroDTO;
import com.herison.componente2.client.LivroClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroClient livroClient;

    public LivroService(LivroClient livroClient) {
        this.livroClient = livroClient;
    }

    public LivroDTO criarLivro(LivroDTO livroDTO){
        return livroClient.criarLivro(livroDTO);
    }

    public List<LivroDTO> buscarTodosLivros(){
        return livroClient.buscarTodosLivros();
    }

    public LivroDTO buscarLivroPorId(Long id) {
        return livroClient.buscarLivroPorId(id);
    }

    public void deletarLivroPorId(Long id) {
        livroClient.deletarLivroPorId(id);
    }

    public void atualizarLivro(Long id, LivroDTO livroDTO){
        LivroDTO livroRequest = new LivroDTO(id, livroDTO.titulo(),
                livroDTO.descricao(), livroDTO.categoria(), livroDTO.autorId());
        livroClient.atualizarLivro(livroRequest);
    }

    public LivroDTO buscarLivroPorTitulo(String titulo) {
        return livroClient.buscarLivroPorTitulo(titulo);
    }

}
