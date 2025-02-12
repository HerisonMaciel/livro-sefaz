package com.herison.componente1.service;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.mapper.AutorMapper;
import com.herison.componente1.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorMapper autorMapper;

    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }

    public AutorDTO criarAutor(AutorDTO autorDTO) {
        Autor autor = autorMapper.toEntity(autorDTO);
        Autor savedAutor = autorRepository.save(autor);
        return autorMapper.toDTO(savedAutor);
    }

    public List<AutorDTO> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AutorDTO buscarAutorPorId(String id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + id));
        return autorMapper.toDTO(autor);
    }

    public AutorDTO buscarAutorPorNome(String nome) {
        Autor autor = autorRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o nome: " + nome));
        return autorMapper.toDTO(autor);
    }

    @Transactional
    public void atualizarAutor(String id, String nome) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com o ID: " + id);
        }
        autorRepository.atualizarAutor(id, nome);
    }

    public void deletarAutorPorId(String id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com o ID: " + id);
        }
        autorRepository.deleteById(id);
    }

}
