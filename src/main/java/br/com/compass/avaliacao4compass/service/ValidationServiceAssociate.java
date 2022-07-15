package br.com.compass.avaliacao4compass.service;

import br.com.compass.avaliacao4compass.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4compass.enums.CargoPoliticoEnum;
import br.com.compass.avaliacao4compass.enums.SexoEnum;
import br.com.compass.avaliacao4compass.exception.CargoPoliticoInvalidException;
import br.com.compass.avaliacao4compass.exception.SexoInvalidException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationServiceAssociate {

    public void validateCargoPolitico(RequestAssociateDTO requestAssociateDTO){
        boolean isValid = Arrays.stream(CargoPoliticoEnum.values()).anyMatch(cargoPoliticoEnum ->
                cargoPoliticoEnum.getValue().equals(requestAssociateDTO.getCargoPolitico()));

        if (!isValid){
            throw new CargoPoliticoInvalidException();
        }
    }

    public void validateSexo(RequestAssociateDTO requestAssociateDTO){
        boolean isValid = Arrays.stream(SexoEnum.values()).anyMatch(sexoEnum ->
                sexoEnum.getValue().equalsIgnoreCase(requestAssociateDTO.getSexo()));

        if (!isValid){
            throw new SexoInvalidException();
        }
    }

}
