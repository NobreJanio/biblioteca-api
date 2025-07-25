/*
 * Testes de Integração - Arquivo 2/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:22:38.474Z
 * Linguagem: java
 * Framework: Spring Test
 */

package com.biblioteca.integration;

import com.biblioteca.controller.EmprestimoController;
import com.biblioteca.domain.model.Emprestimo;
import com.biblioteca.domain.model.Usuario;
import com.biblioteca.domain.model.Livro;
import com.biblioteca.repository.EmprestimoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    private EmprestimoRepository emprestimoRepository;


    // Testar se a criação de um novo empréstimo está funcionando com o banco de dados
    @DataJpaTest
    public void testCreateEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Livro livro = new Livro();
        livro.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        
        emprestimoRepository.save(emprestimo);

        assertThat(emprestimoRepository.count()).isEqualTo(1);
    }

    // Testar a remoção de um empréstimo 
    @DataJpaTest
    public void testDeleteEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Livro livro = new Livro();
        livro.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        
        emprestimoRepository.save(emprestimo);
        emprestimoRepository.delete(emprestimo);

        assertThat(emprestimoRepository.count()).isEqualTo(0);
    }
}
