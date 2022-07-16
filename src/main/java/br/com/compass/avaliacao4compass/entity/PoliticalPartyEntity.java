package br.com.compass.avaliacao4compass.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PoliticalPartyEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    private String ideologia;

    private LocalDate dataFundacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    @JsonBackReference
    private List<AssociateEntity> associados;
}
