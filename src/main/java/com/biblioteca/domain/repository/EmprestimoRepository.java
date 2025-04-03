package com.biblioteca.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
    // Métodos de busca personalizados
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    
    List<Emprestimo> findByLivroId(Long livroId);
    
    List<Emprestimo> findByStatus(String status);
    
    List<Emprestimo> findByDataPrevistaDevolucaoBefore(LocalDate data);
    
    List<Emprestimo> findByUsuarioIdAndStatus(Long usuarioId, String status);
}