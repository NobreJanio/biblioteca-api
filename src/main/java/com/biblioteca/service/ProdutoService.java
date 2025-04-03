package com.biblioteca.service;

import java.math.BigDecimal;
import java.util.List;

import com.biblioteca.domain.model.Produto;

public interface ProdutoService extends CrudService<Long, Produto> {
    
    // Métodos específicos para produtos
    List<Produto> buscarPorNome(String nome);
    
    List<Produto> buscarPorCategoria(Long categoriaId);
    
    List<Produto> buscarAtivos();
    
    List<Produto> buscarInativos();
    
    List<Produto> buscarPorPrecoMaximo(BigDecimal precoMaximo);
    
    List<Produto> buscarPorPrecoMinimo(BigDecimal precoMinimo);
    
    List<Produto> buscarEmEstoque();
}