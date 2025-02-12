package com.herison.componente1.repository;

import com.herison.componente1.entity.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findById(Long id);

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET " +
            "l.titulo = COALESCE(:titulo, l.titulo), " +
            "l.descricao = COALESCE(:descricao, l.descricao), " +
            "l.categoria = COALESCE(:categoria, l.categoria) " +
            "WHERE l.id = :id")
    int atualizarLivro(@Param("id") Long id,
                              @Param("titulo") String titulo,
                              @Param("descricao") String descricao,
                              @Param("categoria") String categoria);
}
