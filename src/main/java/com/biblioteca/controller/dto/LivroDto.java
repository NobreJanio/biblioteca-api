package com.biblioteca.controller.dto;

import com.biblioteca.domain.model.Livro;
import com.biblioteca.domain.model.Categoria;

import java.time.LocalDate;

public record LivroDto(
    Long id,
    String titulo,
    String autor,
    String isbn,
    LocalDate dataPublicacao,
    String editora,
    String sinopse,
    Integer numeroPaginas,
    Boolean disponivel,
    Long categoriaId
) {
    public LivroDto(Livro model) {
        this(
            model.getId(),
            model.getTitulo(),
            model.getAutor(),
            model.getIsbn(),
            model.getDataPublicacao(),
            model.getEditora(),
            model.getSinopse(),
            model.getNumeroPaginas(),
            model.getDisponivel(),
            model.getCategoria() != null ? model.getCategoria().getId() : null
        );
    }

    public Livro toModel() {
        Livro model = new Livro();
        model.setId(this.id);
        model.setTitulo(this.titulo);
        model.setAutor(this.autor);
        model.setIsbn(this.isbn);
        model.setDataPublicacao(this.dataPublicacao);
        model.setEditora(this.editora);
        model.setSinopse(this.sinopse);
        model.setNumeroPaginas(this.numeroPaginas);
        model.setDisponivel(this.disponivel);
        
        if (this.categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(this.categoriaId);
            model.setCategoria(categoria);
        }
        
        return model;
    }
}