package br.com.compass.avaliacao4compass.service;

import br.com.compass.avaliacao4compass.dto.request.RequestPoliticalPartyDTO;
import br.com.compass.avaliacao4compass.enums.politicalParty.IdeologiaEnum;
import br.com.compass.avaliacao4compass.exception.politicalParty.IdeologiaInvalidException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationServicePoliticalParty {
    public void validateIdeologia(RequestPoliticalPartyDTO politicalPartyDTO){
        boolean isValid = Arrays.stream(IdeologiaEnum.values()).anyMatch(ideologiaEnum ->
                ideologiaEnum.getValue().equals(politicalPartyDTO.getIdeologia()));

        if (!isValid){
            throw new IdeologiaInvalidException();
        }
    }
}
