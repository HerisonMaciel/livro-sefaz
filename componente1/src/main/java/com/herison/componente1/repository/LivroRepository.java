package com.herison.componente1.repository;

import com.herison.componente1.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findById(Long id);

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    void deleteById(Long id);
}
