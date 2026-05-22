package org.serratec.projetoapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaResponse {

    private Long id;
    private LocalDateTime dataHora;
    private String observacoes;
    private String status;
    private String paciente;
    private String medico;
    private List<String> exames;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }
    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }
    public List<String> getExames() { return exames; }
    public void setExames(List<String> exames) { this.exames = exames; }
}