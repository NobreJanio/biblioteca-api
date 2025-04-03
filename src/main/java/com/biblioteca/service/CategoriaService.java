package com.biblioteca.service;

import java.util.List;

import com.biblioteca.domain.model.Categoria;

public interface CategoriaService extends CrudService<Long, Categoria> {
    
    // Métodos específicos para categorias
    List<Categoria> buscarPorNome(String nome);
}