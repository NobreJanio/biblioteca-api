package com.biblioteca.service;

import java.util.List;

import com.biblioteca.domain.model.Usuario;

public interface UsuarioService extends CrudService<Long, Usuario> {
    
    // Métodos específicos para usuários
    Usuario buscarPorEmail(String email);
    
    Usuario buscarPorCpf(String cpf);
    
    List<Usuario> buscarPorNome(String nome);
}