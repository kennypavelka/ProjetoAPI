package org.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.projetoapi.domain.Consulta;
import org.serratec.projetoapi.domain.Exame;
import org.serratec.projetoapi.dto.ConsultaRequest;
import org.serratec.projetoapi.dto.ConsultaResponse;
import org.serratec.projetoapi.exception.RecursoNaoEncontradoException;
import org.serratec.projetoapi.repository.ConsultaRepository;
import org.serratec.projetoapi.repository.ExameRepository;
import org.serratec.projetoapi.repository.MedicoRepository;
import org.serratec.projetoapi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ExameRepository exameRepository;

    public List<ConsultaResponse> listar() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ConsultaResponse buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Consulta " + id + " não encontrada"));
        return toResponse(consulta);
    }

    public ConsultaResponse inserir(ConsultaRequest request) {
        Consulta consulta = new Consulta();
        consulta.setDataHora(request.getDataHora());
        consulta.setObservacoes(request.getObservacoes());
        consulta.setStatus(request.getStatus());

        consulta.setPaciente(pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Paciente " + request.getIdPaciente() + " não encontrado")));

        consulta.setMedico(medicoRepository.findById(request.getIdMedico())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Médico " + request.getIdMedico() + " não encontrado")));

        if (request.getIdExames() != null && !request.getIdExames().isEmpty()) {
            List<Exame> exames = exameRepository.findAllById(request.getIdExames());
            consulta.setExames(exames);
        }

        return toResponse(consultaRepository.save(consulta));
    }

    public ConsultaResponse atualizar(Long id, ConsultaRequest request) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Consulta " + id + " não encontrada"));

        consulta.setDataHora(request.getDataHora());
        consulta.setObservacoes(request.getObservacoes());
        consulta.setStatus(request.getStatus());

        consulta.setPaciente(pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Paciente " + request.getIdPaciente() + " não encontrado")));

        consulta.setMedico(medicoRepository.findById(request.getIdMedico())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Médico " + request.getIdMedico() + " não encontrado")));

        if (request.getIdExames() != null && !request.getIdExames().isEmpty()) {
            List<Exame> exames = exameRepository.findAllById(request.getIdExames());
            consulta.setExames(exames);
        }

        return toResponse(consultaRepository.save(consulta));
    }

    public void remover(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Consulta " + id + " não encontrada");
        }
        consultaRepository.deleteById(id);
    }

    private ConsultaResponse toResponse(Consulta consulta) {
        ConsultaResponse response = new ConsultaResponse();
        response.setId(consulta.getId());
        response.setDataHora(consulta.getDataHora());
        response.setObservacoes(consulta.getObservacoes());
        response.setStatus(consulta.getStatus());
        if (consulta.getPaciente() != null) response.setPaciente(consulta.getPaciente().getNome());
        if (consulta.getMedico() != null) response.setMedico(consulta.getMedico().getNome());
        if (consulta.getExames() != null) {
            response.setExames(consulta.getExames().stream()
                    .map(exame -> exame.getNome())
                    .collect(Collectors.toList()));
        }
        return response;
    }
    }