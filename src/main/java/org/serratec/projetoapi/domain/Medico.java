package org.serratec.projetoapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20, unique = true)
    private String crm;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico")
    @JsonBackReference
    private List<Consulta> consultas;
}