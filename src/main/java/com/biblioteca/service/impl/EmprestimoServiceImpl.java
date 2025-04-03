package com.biblioteca.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.domain.model.Emprestimo;
import com.biblioteca.domain.model.Livro;
import com.biblioteca.domain.model.Usuario;
import com.biblioteca.domain.repository.EmprestimoRepository;
import com.biblioteca.domain.repository.LivroRepository;
import com.biblioteca.domain.repository.UsuarioRepository;
import com.biblioteca.service.EmprestimoService;
import com.biblioteca.service.exception.BusinessException;
import com.biblioteca.service.exception.NotFoundException;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    
    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository, 
                                UsuarioRepository usuarioRepository,
                                LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public Emprestimo create(Emprestimo entity) {
        return emprestimoRepository.save(entity);
    }

    @Override
    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com o ID: " + id));
    }

    @Override
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    @Override
    public Emprestimo update(Long id, Emprestimo entity) {
        Emprestimo emprestimo = findById(id);
        
        // Atualiza apenas os campos permitidos
        emprestimo.setStatus(entity.getStatus());
        emprestimo.setDataRealDevolucao(entity.getDataRealDevolucao());
        
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public void delete(Long id) {
        Emprestimo emprestimo = findById(id);
        emprestimoRepository.delete(emprestimo);
    }

    @Override
    public List<Emprestimo> buscarPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Emprestimo> buscarPorLivro(Long livroId) {
        return emprestimoRepository.findByLivroId(livroId);
    }

    @Override
    public List<Emprestimo> buscarPorStatus(String status) {
        return emprestimoRepository.findByStatus(status);
    }

    @Override
    public List<Emprestimo> buscarAtrasados() {
        LocalDate hoje = LocalDate.now();
        return emprestimoRepository.findByDataPrevistaDevolucaoBefore(hoje)
                .stream()
                .filter(emprestimo -> "ATIVO".equals(emprestimo.getStatus()))
                .toList();
    }

    @Override
    public Emprestimo realizarEmprestimo(Long usuarioId, Long livroId, int diasParaDevolucao) {
        // Busca o usuário
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + usuarioId));
        
        // Busca o livro
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado com o ID: " + livroId));
        
        // Verifica se o livro está disponível
        if (livro.getDisponivel() == null || !livro.getDisponivel()) {
            throw new BusinessException("O livro não está disponível para empréstimo");
        }
        
        // Cria o empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(diasParaDevolucao));
        emprestimo.setStatus("ATIVO");
        
        // Atualiza o status do livro
        livro.setDisponivel(false);
        livroRepository.save(livro);
        
        // Salva e retorna o empréstimo
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public Emprestimo realizarDevolucao(Long emprestimoId) {
        // Busca o empréstimo
        Emprestimo emprestimo = findById(emprestimoId);
        
        // Verifica se o empréstimo já foi devolvido
        if ("DEVOLVIDO".equals(emprestimo.getStatus())) {
            throw new BusinessException("Este empréstimo já foi devolvido");
        }
        
        // Atualiza o empréstimo
        emprestimo.setDataRealDevolucao(LocalDate.now());
        emprestimo.setStatus("DEVOLVIDO");
        
        // Atualiza o status do livro
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);
        
        // Salva e retorna o empréstimo
        return emprestimoRepository.save(emprestimo);
    }
}