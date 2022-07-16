package br.com.compass.avaliacao4compass.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class AssociateEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String  cargoPolitico;

    private LocalDate dataNascimento;

    private String sexo;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private PoliticalPartyEntity partido;
}
