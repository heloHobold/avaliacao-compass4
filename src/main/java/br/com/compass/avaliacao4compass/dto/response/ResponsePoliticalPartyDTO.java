package br.com.compass.avaliacao4compass.dto.response;

import br.com.compass.avaliacao4compass.entity.AssociateEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponsePoliticalPartyDTO {

    private Long id;

    private String nome;

    private String sigla;

    private String ideologia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFundacao;

    private List<AssociateEntity> associados;
}
