package com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import com.biblioteca.domain.model.Emprestimo;

public interface EmprestimoService extends CrudService<Long, Emprestimo> {
    
    // Métodos específicos para empréstimos
    List<Emprestimo> buscarPorUsuario(Long usuarioId);
    
    List<Emprestimo> buscarPorLivro(Long livroId);
    
    List<Emprestimo> buscarPorStatus(String status);
    
    List<Emprestimo> buscarAtrasados();
    
    Emprestimo realizarEmprestimo(Long usuarioId, Long livroId, int diasParaDevolucao);
    
    Emprestimo realizarDevolucao(Long emprestimoId);
}