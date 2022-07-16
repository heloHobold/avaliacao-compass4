package br.com.compass.avaliacao4compass.handler;

import br.com.compass.avaliacao4compass.exception.associate.AssociateNotLinkedException;
import br.com.compass.avaliacao4compass.exception.associate.AssociateAlreadyLinkedException;
import br.com.compass.avaliacao4compass.exception.politicalParty.IdeologiaInvalidException;
import br.com.compass.avaliacao4compass.exception.associate.AssociateNotFoundException;
import br.com.compass.avaliacao4compass.exception.associate.CargoPoliticoInvalidException;
import br.com.compass.avaliacao4compass.exception.associate.SexoInvalidException;
import br.com.compass.avaliacao4compass.exception.politicalParty.PoliticalPartyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final String ASSOCIATE_NOT_FOUND = "Associado não encontrado.";
    private static final String CARGO_POLITICO_INVALID = "Cargo político inválido. A primeira letra das palavras deve ser maiúscula. " +
            "Os valores aceitáveis são: Vereador, Prefeito, Deputado Estadual, Deputado Federal, Senador, Governador, Presidente e nenhum.";
    private static final String SEXO_INVALID = "Sexo inválido. OS valores aceitáveis são: Masculino e Feminino";

    private static final String IDEOLOGIA_INVALID = "Ideologia inválida. A primeira letra da palavra deve ser maiúscula. " +
            "Os valores aceitáveis são: Direita, Centro e Esquerda";

    private static final String ERROR_INTERNAL = "Erro interno no servidor.";
    private static final String POLITICAL_PARTY_NOT_FOUND = "Partido não encontrado.";
    private static final String ASSOCIATE_ALREADY_LINKED_INVALID = "O associado já está vinculado a um partido.";
    private static final String ASSOCIATE_NOT_LINKED = "O associado informado ainda não está vinculado a nenhum partido.";

    @ExceptionHandler(value = {AssociateNotFoundException.class})
    protected ResponseEntity<ErrorMassage> handlerAssociateNotFound(AssociateNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMassage(ASSOCIATE_NOT_FOUND));
    }

    @ExceptionHandler(value = {PoliticalPartyNotFoundException.class})
    protected ResponseEntity<ErrorMassage> handlerPoliticalPartyNotFound(PoliticalPartyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMassage(POLITICAL_PARTY_NOT_FOUND));
    }

    @ExceptionHandler(value = {CargoPoliticoInvalidException.class})
    protected ResponseEntity<ErrorMassage> handlerCargoPoliticoInvalid(CargoPoliticoInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(CARGO_POLITICO_INVALID));
    }

    @ExceptionHandler(value = {SexoInvalidException.class})
    protected ResponseEntity<ErrorMassage> handlerSexoInvalid(SexoInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(SEXO_INVALID));
    }

    @ExceptionHandler(value = {IdeologiaInvalidException.class})
    protected ResponseEntity<ErrorMassage> handlerIdeologiaInvalid(IdeologiaInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(IDEOLOGIA_INVALID));
    }

    @ExceptionHandler(value = {AssociateAlreadyLinkedException.class})
    protected ResponseEntity<ErrorMassage> handlerAssociateAlreadyLinked(AssociateAlreadyLinkedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(ASSOCIATE_ALREADY_LINKED_INVALID));
    }

    @ExceptionHandler(value = {AssociateNotLinkedException.class})
    protected ResponseEntity<ErrorMassage> handlerAssociateNotLinked(AssociateNotLinkedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(ASSOCIATE_NOT_LINKED));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorMassage> handlerException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMassage(ERROR_INTERNAL));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
            "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMassage(validationList));
    }

}
