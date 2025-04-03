package com.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.model.Emprestimo;
import com.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    private final EmprestimoService emprestimoService;
    
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }
    
    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.findById(id));
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Emprestimo>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(emprestimoService.buscarPorUsuario(usuarioId));
    }
    
    @GetMapping("/livro/{livroId}")
    public ResponseEntity<List<Emprestimo>> buscarPorLivro(@PathVariable Long livroId) {
        return ResponseEntity.ok(emprestimoService.buscarPorLivro(livroId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Emprestimo>> buscarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(emprestimoService.buscarPorStatus(status));
    }
    
    @GetMapping("/atrasados")
    public ResponseEntity<List<Emprestimo>> buscarAtrasados() {
        return ResponseEntity.ok(emprestimoService.buscarAtrasados());
    }
    
    @PostMapping
    public ResponseEntity<Emprestimo> criar(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.create(emprestimo));
    }
    
    @PostMapping("/realizar")
    public ResponseEntity<Emprestimo> realizarEmprestimo(
            @RequestParam Long usuarioId, 
            @RequestParam Long livroId, 
            @RequestParam(defaultValue = "7") int diasParaDevolucao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestimoService.realizarEmprestimo(usuarioId, livroId, diasParaDevolucao));
    }
    
    @PostMapping("/devolver/{id}")
    public ResponseEntity<Emprestimo> realizarDevolucao(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.realizarDevolucao(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        return ResponseEntity.ok(emprestimoService.update(id, emprestimo));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}