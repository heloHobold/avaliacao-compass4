package br.com.compass.avaliacao4compass.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMassage {

    private String menssage;

    private List<String> validationsErrors;

    public ErrorMassage(String menssage){
        this.menssage = menssage;
    }

    public ErrorMassage(List<String> validationsErrors){
        this.validationsErrors = validationsErrors;
    }
}
