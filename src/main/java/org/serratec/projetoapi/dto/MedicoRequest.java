package org.serratec.projetoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedicoRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Schema(description = "Nome do médico", example = "Dr. Carlos Souza")
    private String nome;

    @NotBlank(message = "O CRM é obrigatório.")
    @Size(max = 20, message = "O CRM deve ter no máximo 20 caracteres.")
    @Schema(description = "CRM do médico", example = "CRM/RJ 123456")
    private String crm;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    @Schema(description = "E-mail do médico", example = "carlos@clinica.com")
    private String email;

    @Schema(description = "ID da especialidade", example = "1")
    private Long idEspecialidade;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getIdEspecialidade() { return idEspecialidade; }
    public void setIdEspecialidade(Long idEspecialidade) { this.idEspecialidade = idEspecialidade; }
}