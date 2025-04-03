package com.biblioteca.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.model.Livro;
import com.biblioteca.domain.repository.LivroRepository;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.exception.BusinessException;
import com.biblioteca.service.exception.NotFoundException;

import static java.util.Optional.ofNullable;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional(readOnly = true)
    public List<Livro> findAll() {
        return this.livroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Livro findById(Long id) {
        return this.livroRepository.findById(id).orElseThrow(() -> new NotFoundException("Livro não encontrado."));
    }

    @Transactional
    public Livro create(Livro livroToCreate) {
        ofNullable(livroToCreate).orElseThrow(() -> new BusinessException("Livro não pode ser nulo."));
        
        if (livroToCreate.getId() != null) {
            throw new BusinessException("ID do livro deve ser nulo para criação.");
        }
        
        if (livroRepository.existsByIsbn(livroToCreate.getIsbn())) {
            throw new BusinessException("Já existe um livro com este ISBN.");
        }
        
        return this.livroRepository.save(livroToCreate);
    }

    @Transactional
    public Livro update(Long id, Livro livroToUpdate) {
        ofNullable(livroToUpdate).orElseThrow(() -> new BusinessException("Livro não pode ser nulo."));
        
        Livro livroExistente = this.findById(id);
        
        if (!livroExistente.getIsbn().equals(livroToUpdate.getIsbn()) && livroRepository.existsByIsbn(livroToUpdate.getIsbn())) {
            throw new BusinessException("Já existe um livro com este ISBN.");
        }
        
        livroToUpdate.setId(id);
        return this.livroRepository.save(livroToUpdate);
    }

    @Transactional
    public void delete(Long id) {
        Livro livroExistente = this.findById(id);
        this.livroRepository.delete(livroExistente);
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarPorTitulo(String titulo) {
        return this.livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarPorAutor(String autor) {
        return this.livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarPorCategoria(Long categoriaId) {
        return this.livroRepository.findByCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarDisponiveis() {
        return this.livroRepository.findByDisponivel(true);
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarIndisponiveis() {
        return this.livroRepository.findByDisponivel(false);
    }
}