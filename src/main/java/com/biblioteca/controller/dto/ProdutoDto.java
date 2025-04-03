package com.biblioteca.controller.dto;

import com.biblioteca.domain.model.Produto;
import com.biblioteca.domain.model.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoDto(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Integer quantidadeEstoque,
    String codigoBarras,
    LocalDateTime dataCadastro,
    Boolean ativo,
    Long categoriaId
) {
    public ProdutoDto(Produto model) {
        this(
            model.getId(),
            model.getNome(),
            model.getDescricao(),
            model.getPreco(),
            model.getQuantidadeEstoque(),
            model.getCodigoBarras(),
            model.getDataCadastro(),
            model.getAtivo(),
            model.getCategoria() != null ? model.getCategoria().getId() : null
        );
    }

    public Produto toModel() {
        Produto model = new Produto();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setPreco(this.preco);
        model.setQuantidadeEstoque(this.quantidadeEstoque);
        model.setCodigoBarras(this.codigoBarras);
        model.setDataCadastro(this.dataCadastro);
        model.setAtivo(this.ativo);
        
        if (this.categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(this.categoriaId);
            model.setCategoria(categoria);
        }
        
        return model;
    }
}