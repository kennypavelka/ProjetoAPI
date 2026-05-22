package org.serratec.projetoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaRequest {

    @NotNull(message = "A data e hora são obrigatórias.")
    @Schema(description = "Data e hora da consulta", example = "2026-06-15T10:00:00")
    private LocalDateTime dataHora;

    @Size(max = 300, message = "As observações devem ter no máximo 300 caracteres.")
    @Schema(description = "Observações da consulta", example = "Paciente com histórico de hipertensão")
    private String observacoes;

    @NotBlank(message = "O status é obrigatório.")
    @Schema(description = "Status da consulta", example = "AGENDADA")
    private String status;

    @NotNull(message = "O paciente é obrigatório.")
    @Schema(description = "ID do paciente", example = "1")
    private Long idPaciente;

    @NotNull(message = "O médico é obrigatório.")
    @Schema(description = "ID do médico", example = "1")
    private Long idMedico;

    @Schema(description = "Lista de IDs dos exames", example = "[1, 2]")
    private List<Long> idExames;

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }
    public List<Long> getIdExames() { return idExames; }
    public void setIdExames(List<Long> idExames) { this.idExames = idExames; }
}