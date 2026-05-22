package org.serratec.projetoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class PacienteRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Schema(description = "Nome do paciente", example = "Ana Lima")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    @Schema(description = "E-mail do paciente", example = "ana@email.com")
    private String email;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 14, message = "CPF inválido.")
    @Schema(description = "CPF do paciente", example = "123.456.789-00")
    private String cpf;

    @Past(message = "A data de nascimento deve estar no passado.")
    @Schema(description = "Data de nascimento", example = "1990-05-10")
    private LocalDate dataNascimento;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
    @Schema(description = "Telefone do paciente", example = "(21) 99999-9999")
    private String telefone;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}