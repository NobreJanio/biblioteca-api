package com.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.biblioteca.controller.dto.LivroDto;
import com.biblioteca.service.LivroService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/livros")
@Tag(name = "Livros Controller", description = "API RESTful para gerenciamento de livros.")
public record LivroController(LivroService livroService) {

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Recupera uma lista de todos os livros cadastrados")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    })
    public ResponseEntity<List<LivroDto>> findAll() {
        var livros = livroService.findAll();
        var livrosDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Recupera um livro específico com base no seu ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<LivroDto> findById(@PathVariable Long id) {
        var livro = livroService.findById(id);
        return ResponseEntity.ok(new LivroDto(livro));
    }

    @PostMapping
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro e retorna os dados do livro criado")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados do livro inválidos")
    })
    public ResponseEntity<LivroDto> create(@RequestBody LivroDto livroDto) {
        var livro = livroService.create(livroDto.toModel());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();
        return ResponseEntity.created(location).body(new LivroDto(livro));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um livro", description = "Atualiza os dados de um livro existente")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados do livro inválidos")
    })
    public ResponseEntity<LivroDto> update(@PathVariable Long id, @RequestBody LivroDto livroDto) {
        var livro = livroService.update(id, livroDto.toModel());
        return ResponseEntity.ok(new LivroDto(livro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um livro", description = "Remove um livro do sistema")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    @Operation(summary = "Buscar livros por título", description = "Recupera livros que contenham o título especificado")
    public ResponseEntity<List<LivroDto>> findByTitulo(@PathVariable String titulo) {
        var livros = livroService.buscarPorTitulo(titulo);
        var livrosDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/autor/{autor}")
    @Operation(summary = "Buscar livros por autor", description = "Recupera livros que contenham o autor especificado")
    public ResponseEntity<List<LivroDto>> findByAutor(@PathVariable String autor) {
        var livros = livroService.buscarPorAutor(autor);
        var livrosDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Buscar livros por categoria", description = "Recupera livros da categoria especificada")
    public ResponseEntity<List<LivroDto>> findByCategoria(@PathVariable Long categoriaId) {
        var livros = livroService.buscarPorCategoria(categoriaId);
        var livrosDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/disponiveis")
    @Operation(summary = "Buscar livros disponíveis", description = "Recupera todos os livros disponíveis para empréstimo")
    public ResponseEntity<List<LivroDto>> findDisponiveis() {
        var livros = livroService.buscarDisponiveis();
        var livrosDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDto);
    }
}