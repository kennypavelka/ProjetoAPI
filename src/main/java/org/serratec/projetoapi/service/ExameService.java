package org.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.projetoapi.domain.Exame;
import org.serratec.projetoapi.dto.ExameRequest;
import org.serratec.projetoapi.dto.ExameResponse;
import org.serratec.projetoapi.exception.RecursoNaoEncontradoException;
import org.serratec.projetoapi.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<ExameResponse> listar() {
        return exameRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ExameResponse buscarPorId(Long id) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Exame " + id + " não encontrado"));
        return toResponse(exame);
    }

    public ExameResponse inserir(ExameRequest request) {
        Exame exame = new Exame();
        exame.setNome(request.getNome());
        exame.setDescricao(request.getDescricao());
        exame.setValor(request.getValor());
        return toResponse(exameRepository.save(exame));
    }

    public ExameResponse atualizar(Long id, ExameRequest request) {
        if (!exameRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Exame " + id + " não encontrado");
        }
        Exame exame = new Exame();
        exame.setId(id);
        exame.setNome(request.getNome());
        exame.setDescricao(request.getDescricao());
        exame.setValor(request.getValor());
        return toResponse(exameRepository.save(exame));
    }

    public void remover(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Exame " + id + " não encontrado");
        }
        exameRepository.deleteById(id);
    }

    private ExameResponse toResponse(Exame exame) {
        ExameResponse response = new ExameResponse();
        response.setId(exame.getId());
        response.setNome(exame.getNome());
        response.setDescricao(exame.getDescricao());
        response.setValor(exame.getValor());
        return response;
    }
}