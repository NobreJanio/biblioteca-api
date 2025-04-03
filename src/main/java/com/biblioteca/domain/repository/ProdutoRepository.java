package com.biblioteca.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    boolean existsByCodigoBarras(String codigoBarras);
    
    // Métodos de busca personalizados
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    List<Produto> findByCategoriaId(Long categoriaId);
    
    List<Produto> findByAtivo(Boolean ativo);
    
    List<Produto> findByPrecoLessThanEqual(BigDecimal precoMaximo);
    
    List<Produto> findByPrecoGreaterThanEqual(BigDecimal precoMinimo);
    
    List<Produto> findByQuantidadeEstoqueGreaterThan(Integer quantidade);
}