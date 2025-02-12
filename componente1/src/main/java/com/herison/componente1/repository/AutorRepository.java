package com.herison.componente1.repository;

import com.herison.componente1.entity.Autor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    Optional<Autor> findById(String id);

    Optional<Autor> findByNomeIgnoreCase(String nome);

    void deleteById(String id);

    @Transactional
    @Modifying
    @Query("UPDATE Autor a SET " +
            "a.nome = COALESCE(:nome, a.nome) " +
            "WHERE a.id = :id")
    int atualizarAutor(@Param("id") String id,
                       @Param("nome") String nome);

}

