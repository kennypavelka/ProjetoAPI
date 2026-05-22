package org.serratec.projetoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EspecialidadeRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres.")
    @Schema(description = "Nome da especialidade", example = "Cardiologia")
    private String nome;

    @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres.")
    @Schema(description = "Descrição da especialidade", example = "Especialidade voltada ao coração")
    private String descricao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}