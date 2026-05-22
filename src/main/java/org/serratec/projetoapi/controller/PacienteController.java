package org.serratec.projetoapi.controller;

import java.util.List;

import org.serratec.projetoapi.dto.PacienteRequest;
import org.serratec.projetoapi.dto.PacienteResponse;
import org.serratec.projetoapi.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Lista todos os pacientes")
    @GetMapping
    public List<PacienteResponse> listar() {
        return pacienteService.listar();
    }

    @Operation(summary = "Busca paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @Operation(summary = "Cadastra novo paciente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse inserir(@Valid @RequestBody PacienteRequest request) {
        return pacienteService.inserir(request);
    }

    @Operation(summary = "Atualiza paciente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id,
                                                      @Valid @RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteService.atualizar(id, request));
    }

    @Operation(summary = "Remove paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pacienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}