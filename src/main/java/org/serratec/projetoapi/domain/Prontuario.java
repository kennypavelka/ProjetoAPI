package org.serratec.projetoapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String historico;

    @Column(length = 200)
    private String alergias;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}