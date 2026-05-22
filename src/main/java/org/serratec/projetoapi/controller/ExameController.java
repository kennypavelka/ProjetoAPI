package org.serratec.projetoapi.controller;

import java.util.List;

import org.serratec.projetoapi.dto.ExameRequest;
import org.serratec.projetoapi.dto.ExameResponse;
import org.serratec.projetoapi.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @Operation(summary = "Lista todos os exames")
    @GetMapping
    public List<ExameResponse> listar() {
        return exameService.listar();
    }

    @Operation(summary = "Busca exame por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExameResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(exameService.buscarPorId(id));
    }

    @Operation(summary = "Cadastra novo exame")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExameResponse inserir(@Valid @RequestBody ExameRequest request) {
        return exameService.inserir(request);
    }

    @Operation(summary = "Atualiza exame")
    @PutMapping("/{id}")
    public ResponseEntity<ExameResponse> atualizar(@PathVariable Long id,
                                                   @Valid @RequestBody ExameRequest request) {
        return ResponseEntity.ok(exameService.atualizar(id, request));
    }

    @Operation(summary = "Remove exame")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        exameService.remover(id);
        return ResponseEntity.noContent().build();
    }
}