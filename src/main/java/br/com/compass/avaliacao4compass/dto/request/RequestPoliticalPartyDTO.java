package br.com.compass.avaliacao4compass.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class RequestPoliticalPartyDTO {

    @NotBlank
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]{3,}$", message = ": Nome deve conter somente e pelo menos três letras.")
    private String nome;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2,}$", message = ": Sigla deve conter somente e pelo menos duas letras todas em maiúsculo.")
    private String sigla;

    @NotBlank
    private String ideologia;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFundacao;
}
