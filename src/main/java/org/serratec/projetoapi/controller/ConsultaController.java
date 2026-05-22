package org.serratec.projetoapi.controller;

import java.util.List;

import org.serratec.projetoapi.dto.ConsultaRequest;
import org.serratec.projetoapi.dto.ConsultaResponse;
import org.serratec.projetoapi.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Operation(summary = "Lista todas as consultas")
    @GetMapping
    public List<ConsultaResponse> listar() {
        return consultaService.listar();
    }

    @Operation(summary = "Busca consulta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @Operation(summary = "Agenda nova consulta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponse inserir(@Valid @RequestBody ConsultaRequest request) {
        return consultaService.inserir(request);
    }

    @Operation(summary = "Atualiza consulta")
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable Long id,
                                                      @Valid @RequestBody ConsultaRequest request) {
        return ResponseEntity.ok(consultaService.atualizar(id, request));
    }

    @Operation(summary = "Remove consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        consultaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}