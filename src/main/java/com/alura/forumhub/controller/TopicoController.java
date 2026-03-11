package com.alura.forumhub.controller;

import com.alura.forumhub.dto.DadosCadastroTopico;
import com.alura.forumhub.entity.Topico;
import com.alura.forumhub.repository.TopicoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository repository;

    // Criar tópico
    @PostMapping
    public ResponseEntity<Topico> criar(@RequestBody @Valid DadosCadastroTopico dados) {

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        repository.save(topico);

        return ResponseEntity.ok(topico);
    }

    // Listar todos os tópicos
    @GetMapping
    public List<Topico> listar() {
        return repository.findAll();
    }

    // Buscar tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarPorId(@PathVariable Long id) {

        Optional<Topico> topico = repository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Atualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroTopico dados) {

        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        repository.save(topico);

        return ResponseEntity.ok(topico);
    }

    // Deletar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}