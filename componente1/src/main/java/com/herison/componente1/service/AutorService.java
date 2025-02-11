package com.herison.componente1.service;

import com.herison.componente1.Dtos.AutorDTO;
import com.herison.componente1.entity.Autor;
import com.herison.componente1.mapper.AutorMapper;
import com.herison.componente1.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AutorService {

    private AutorRepository autorRepository;

    private AutorMapper autorMapper;

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

}
