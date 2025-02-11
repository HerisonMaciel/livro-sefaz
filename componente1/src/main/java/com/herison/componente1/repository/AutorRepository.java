package com.herison.componente1.repository;

import com.herison.componente1.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    Optional<Autor> findById(String id);

    Optional<Autor> findByNomeIgnoreCase(String nome);

    void deleteById(String id);
}
