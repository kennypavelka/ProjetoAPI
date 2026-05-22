package org.serratec.projetoapi.controller;

import java.util.List;

import org.serratec.projetoapi.dto.EspecialidadeRequest;
import org.serratec.projetoapi.dto.EspecialidadeResponse;
import org.serratec.projetoapi.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Operation(summary = "Lista todas as especialidades")
    @GetMapping
    public List<EspecialidadeResponse> listar() {
        return especialidadeService.listar();
    }

    @Operation(summary = "Busca especialidade por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @Operation(summary = "Cadastra nova especialidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EspecialidadeResponse inserir(@Valid @RequestBody EspecialidadeRequest request) {
        return especialidadeService.inserir(request);
    }

    @Operation(summary = "Atualiza especialidade")
    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> atualizar(@PathVariable Long id,
                                                           @Valid @RequestBody EspecialidadeRequest request) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, request));
    }

    @Operation(summary = "Remove especialidade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        especialidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}