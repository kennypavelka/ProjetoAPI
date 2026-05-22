package org.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.projetoapi.domain.Paciente;
import org.serratec.projetoapi.dto.PacienteRequest;
import org.serratec.projetoapi.dto.PacienteResponse;
import org.serratec.projetoapi.exception.RecursoNaoEncontradoException;
import org.serratec.projetoapi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteResponse> listar() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Paciente " + id + " não encontrado"));
        return toResponse(paciente);
    }

    public PacienteResponse inserir(PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setEmail(request.getEmail());
        paciente.setCpf(request.getCpf());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setTelefone(request.getTelefone());
        return toResponse(pacienteRepository.save(paciente));
    }

    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Paciente " + id + " não encontrado"));

        paciente.setNome(request.getNome());
        paciente.setEmail(request.getEmail());
        paciente.setCpf(request.getCpf());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setTelefone(request.getTelefone());
        return toResponse(pacienteRepository.save(paciente));
    }

    public void remover(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Paciente " + id + " não encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteResponse toResponse(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setEmail(paciente.getEmail());
        response.setCpf(paciente.getCpf());
        response.setDataNascimento(paciente.getDataNascimento());
        response.setTelefone(paciente.getTelefone());
        return response;
    }
}