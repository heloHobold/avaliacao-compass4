package br.com.compass.avaliacao4compass.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseAssociateDTO {

    private Long id;

    private String nome;

    private String cargoPolitico;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String sexo;

    //private PoliticalPartyEntity partido;
}
