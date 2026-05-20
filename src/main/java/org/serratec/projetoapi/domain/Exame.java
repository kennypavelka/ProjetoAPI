package org.serratec.projetoapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "exame")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 200)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @ManyToMany(mappedBy = "exames")
    @JsonIgnore
    private List<Consulta> consultas;
}