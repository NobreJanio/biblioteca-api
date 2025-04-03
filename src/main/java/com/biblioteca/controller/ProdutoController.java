package com.biblioteca.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.biblioteca.controller.dto.ProdutoDto;
import com.biblioteca.service.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos Controller", description = "API RESTful para gerenciamento de produtos.")
public record ProdutoController(ProdutoService produtoService) {

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Recupera uma lista de todos os produtos cadastrados")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    })
    public ResponseEntity<List<ProdutoDto>> findAll() {
        var produtos = produtoService.findAll();
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Recupera um produto específico com base no seu ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        var produto = produtoService.findById(id);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto e retorna os dados do produto criado")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados do produto inválidos")
    })
    public ResponseEntity<ProdutoDto> create(@RequestBody ProdutoDto produtoDto) {
        var produto = produtoService.create(produtoDto.toModel());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ProdutoDto(produto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados do produto inválidos")
    })
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        var produto = produtoService.update(id, produtoDto.toModel());
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto", description = "Remove um produto do sistema")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar produtos por nome", description = "Recupera produtos que contenham o nome especificado")
    public ResponseEntity<List<ProdutoDto>> findByNome(@PathVariable String nome) {
        var produtos = produtoService.buscarPorNome(nome);
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Buscar produtos por categoria", description = "Recupera produtos da categoria especificada")
    public ResponseEntity<List<ProdutoDto>> findByCategoria(@PathVariable Long categoriaId) {
        var produtos = produtoService.buscarPorCategoria(categoriaId);
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/ativos")
    @Operation(summary = "Buscar produtos ativos", description = "Recupera todos os produtos ativos")
    public ResponseEntity<List<ProdutoDto>> findAtivos() {
        var produtos = produtoService.buscarAtivos();
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/inativos")
    @Operation(summary = "Buscar produtos inativos", description = "Recupera todos os produtos inativos")
    public ResponseEntity<List<ProdutoDto>> findInativos() {
        var produtos = produtoService.buscarInativos();
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/preco-maximo/{precoMaximo}")
    @Operation(summary = "Buscar produtos por preço máximo", description = "Recupera produtos com preço menor ou igual ao especificado")
    public ResponseEntity<List<ProdutoDto>> findByPrecoMaximo(@PathVariable BigDecimal precoMaximo) {
        var produtos = produtoService.buscarPorPrecoMaximo(precoMaximo);
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/preco-minimo/{precoMinimo}")
    @Operation(summary = "Buscar produtos por preço mínimo", description = "Recupera produtos com preço maior ou igual ao especificado")
    public ResponseEntity<List<ProdutoDto>> findByPrecoMinimo(@PathVariable BigDecimal precoMinimo) {
        var produtos = produtoService.buscarPorPrecoMinimo(precoMinimo);
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/em-estoque")
    @Operation(summary = "Buscar produtos em estoque", description = "Recupera todos os produtos com quantidade em estoque maior que zero")
    public ResponseEntity<List<ProdutoDto>> findEmEstoque() {
        var produtos = produtoService.buscarEmEstoque();
        var produtosDto = produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtosDto);
    }
}