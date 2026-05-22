package org.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.projetoapi.domain.Especialidade;
import org.serratec.projetoapi.dto.EspecialidadeRequest;
import org.serratec.projetoapi.dto.EspecialidadeResponse;
import org.serratec.projetoapi.exception.RecursoNaoEncontradoException;
import org.serratec.projetoapi.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<EspecialidadeResponse> listar() {
        return especialidadeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EspecialidadeResponse buscarPorId(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Especialidade " + id + " não encontrada"));
        return toResponse(especialidade);
    }

    public EspecialidadeResponse inserir(EspecialidadeRequest request) {
        Especialidade especialidade = new Especialidade();
        especialidade.setNome(request.getNome());
        especialidade.setDescricao(request.getDescricao());
        return toResponse(especialidadeRepository.save(especialidade));
    }

    public EspecialidadeResponse atualizar(Long id, EspecialidadeRequest request) {
        if (!especialidadeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Especialidade " + id + " não encontrada");
        }
        Especialidade especialidade = new Especialidade();
        especialidade.setId(id);
        especialidade.setNome(request.getNome());
        especialidade.setDescricao(request.getDescricao());
        return toResponse(especialidadeRepository.save(especialidade));
    }

    public void remover(Long id) {
        if (!especialidadeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Especialidade " + id + " não encontrada");
        }
        especialidadeRepository.deleteById(id);
    }

    private EspecialidadeResponse toResponse(Especialidade especialidade) {
        EspecialidadeResponse response = new EspecialidadeResponse();
        response.setId(especialidade.getId());
        response.setNome(especialidade.getNome());
        response.setDescricao(especialidade.getDescricao());
        return response;
    }
}