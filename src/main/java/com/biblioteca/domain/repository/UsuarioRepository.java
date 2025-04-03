package com.biblioteca.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    boolean existsByEmail(String email);
    
    boolean existsByCpf(String cpf);
    
    // Métodos de busca personalizados
    Usuario findByEmail(String email);
    
    Usuario findByCpf(String cpf);
    
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}