/*
 * Testes de Integração - Arquivo 1/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:22:11.959Z
 * Linguagem: java
 * Framework: Spring Test
 */

package com.biblioteca.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.biblioteca.domain.model.Emprestimo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmprestimoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String EMPRESTIMOS_ENDPOINT = "/emprestimos";
    private Emprestimo novoEmprestimo;

    @BeforeEach
    public void setUp() {
        novoEmprestimo = new Emprestimo();
        // preencha o objeto de emprestimo conforme necessário
    }

    @Test
    public void testCreateEmprestimo() {
        ResponseEntity<Emprestimo> responseEntity = restTemplate.postForEntity("http://localhost:" + port + EMPRESTIMOS_ENDPOINT, novoEmprestimo, Emprestimo.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isNotNull();
        // Insira mais verificações conforme necessário
    }

    // Repita para outros casos de teste, como buscarPorId, buscarPorUsuario, etc.
}
