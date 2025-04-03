package com.biblioteca.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    boolean existsByIsbn(String isbn);
    
    // Métodos de busca personalizados
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    
    List<Livro> findByCategoriaId(Long categoriaId);
    
    List<Livro> findByDisponivel(Boolean disponivel);
}