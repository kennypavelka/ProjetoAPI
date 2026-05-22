package org.serratec.projetoapi.controller;

import java.util.List;

import org.serratec.projetoapi.dto.MedicoRequest;
import org.serratec.projetoapi.dto.MedicoResponse;
import org.serratec.projetoapi.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Operation(summary = "Lista todos os médicos")
    @GetMapping
    public List<MedicoResponse> listar() {
        return medicoService.listar();
    }

    @Operation(summary = "Busca médico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @Operation(summary = "Cadastra novo médico")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoResponse inserir(@Valid @RequestBody MedicoRequest request) {
        return medicoService.inserir(request);
    }

    @Operation(summary = "Atualiza médico")
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody MedicoRequest request) {
        return ResponseEntity.ok(medicoService.atualizar(id, request));
    }

    @Operation(summary = "Remove médico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        medicoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}