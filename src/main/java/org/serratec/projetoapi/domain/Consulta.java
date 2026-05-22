package org.serratec.projetoapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 300)
    private String observacoes;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToMany
    @JoinTable(
            name = "consulta_exame",
            joinColumns = @JoinColumn(name = "id_consulta"),
            inverseJoinColumns = @JoinColumn(name = "id_exame")
    )
    private List<Exame> exames;
}