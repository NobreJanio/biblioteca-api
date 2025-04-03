package com.biblioteca.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.model.Produto;
import com.biblioteca.domain.repository.ProdutoRepository;
import com.biblioteca.service.ProdutoService;
import com.biblioteca.service.exception.BusinessException;
import com.biblioteca.service.exception.NotFoundException;

import static java.util.Optional.ofNullable;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto findById(Long id) {
        return this.produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    @Transactional
    public Produto create(Produto produtoToCreate) {
        ofNullable(produtoToCreate).orElseThrow(() -> new BusinessException("Produto não pode ser nulo."));
        
        if (produtoToCreate.getId() != null) {
            throw new BusinessException("ID do produto deve ser nulo para criação.");
        }
        
        if (produtoToCreate.getCodigoBarras() != null && produtoRepository.existsByCodigoBarras(produtoToCreate.getCodigoBarras())) {
            throw new BusinessException("Já existe um produto com este código de barras.");
        }
        
        // Define a data de cadastro como a data atual
        if (produtoToCreate.getDataCadastro() == null) {
            produtoToCreate.setDataCadastro(LocalDateTime.now());
        }
        
        return this.produtoRepository.save(produtoToCreate);
    }

    @Transactional
    public Produto update(Long id, Produto produtoToUpdate) {
        ofNullable(produtoToUpdate).orElseThrow(() -> new BusinessException("Produto não pode ser nulo."));
        
        Produto produtoExistente = this.findById(id);
        
        if (produtoToUpdate.getCodigoBarras() != null && 
            !produtoToUpdate.getCodigoBarras().equals(produtoExistente.getCodigoBarras()) && 
            produtoRepository.existsByCodigoBarras(produtoToUpdate.getCodigoBarras())) {
            throw new BusinessException("Já existe um produto com este código de barras.");
        }
        
        // Mantém a data de cadastro original
        produtoToUpdate.setDataCadastro(produtoExistente.getDataCadastro());
        produtoToUpdate.setId(id);
        return this.produtoRepository.save(produtoToUpdate);
    }

    @Transactional
    public void delete(Long id) {
        Produto produtoExistente = this.findById(id);
        this.produtoRepository.delete(produtoExistente);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        return this.produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return this.produtoRepository.findByCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarAtivos() {
        return this.produtoRepository.findByAtivo(true);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarInativos() {
        return this.produtoRepository.findByAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorPrecoMaximo(BigDecimal precoMaximo) {
        return this.produtoRepository.findByPrecoLessThanEqual(precoMaximo);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorPrecoMinimo(BigDecimal precoMinimo) {
        return this.produtoRepository.findByPrecoGreaterThanEqual(precoMinimo);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarEmEstoque() {
        return this.produtoRepository.findByQuantidadeEstoqueGreaterThan(0);
    }
}