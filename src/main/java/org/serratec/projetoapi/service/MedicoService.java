package org.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.projetoapi.domain.Medico;
import org.serratec.projetoapi.dto.MedicoRequest;
import org.serratec.projetoapi.dto.MedicoResponse;
import org.serratec.projetoapi.exception.RecursoNaoEncontradoException;
import org.serratec.projetoapi.repository.EspecialidadeRepository;
import org.serratec.projetoapi.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<MedicoResponse> listar() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MedicoResponse buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Médico " + id + " não encontrado"));
        return toResponse(medico);
    }

    public MedicoResponse inserir(MedicoRequest request) {
        Medico medico = new Medico();
        medico.setNome(request.getNome());
        medico.setCrm(request.getCrm());
        medico.setEmail(request.getEmail());

        if (request.getIdEspecialidade() != null) {
            medico.setEspecialidade(especialidadeRepository.findById(request.getIdEspecialidade())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Especialidade " + request.getIdEspecialidade() + " não encontrada")));
        }

        return toResponse(medicoRepository.save(medico));
    }

    public MedicoResponse atualizar(Long id, MedicoRequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Médico " + id + " não encontrado"));

        medico.setNome(request.getNome());
        medico.setCrm(request.getCrm());
        medico.setEmail(request.getEmail());

        if (request.getIdEspecialidade() != null) {
            medico.setEspecialidade(especialidadeRepository.findById(request.getIdEspecialidade())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Especialidade " + request.getIdEspecialidade() + " não encontrada")));
        }

        return toResponse(medicoRepository.save(medico));
    }

    public void remover(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Médico " + id + " não encontrado");
        }
        medicoRepository.deleteById(id);
    }

    private MedicoResponse toResponse(Medico medico) {
        MedicoResponse response = new MedicoResponse();
        response.setId(medico.getId());
        response.setNome(medico.getNome());
        response.setCrm(medico.getCrm());
        response.setEmail(medico.getEmail());
        if (medico.getEspecialidade() != null) {
            response.setEspecialidade(medico.getEspecialidade().getNome());
        }
        return response;
    }
}