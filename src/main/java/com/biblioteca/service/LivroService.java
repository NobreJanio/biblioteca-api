package com.biblioteca.service;

import java.util.List;

import com.biblioteca.domain.model.Livro;

public interface LivroService extends CrudService<Long, Livro> {
    
    // Métodos específicos para livros
    List<Livro> buscarPorTitulo(String titulo);
    
    List<Livro> buscarPorAutor(String autor);
    
    List<Livro> buscarPorCategoria(Long categoriaId);
    
    List<Livro> buscarDisponiveis();
    
    List<Livro> buscarIndisponiveis();
}