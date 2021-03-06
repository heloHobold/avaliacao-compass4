package br.com.compass.avaliacao4compass.service;

import br.com.compass.avaliacao4compass.dto.request.RequestPoliticalPartyDTO;
import br.com.compass.avaliacao4compass.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4compass.dto.response.ResponsePoliticalPartyDTO;
import br.com.compass.avaliacao4compass.entity.AssociateEntity;
import br.com.compass.avaliacao4compass.entity.PoliticalPartyEntity;
import br.com.compass.avaliacao4compass.exception.politicalParty.PoliticalPartyNotFoundException;
import br.com.compass.avaliacao4compass.repository.AssociateRepository;
import br.com.compass.avaliacao4compass.repository.PoliticalPartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PoliticalPartyService {

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private ValidationServicePoliticalParty validationServicePoliticalParty;
    @Autowired
    private ModelMapper modelMapper;

    public ResponsePoliticalPartyDTO save(RequestPoliticalPartyDTO politicalPartyDTO, UriComponentsBuilder uriComponentsBuilder){
        validationServicePoliticalParty.validateIdeologia(politicalPartyDTO) ;
        PoliticalPartyEntity entity = modelMapper.map(politicalPartyDTO, PoliticalPartyEntity.class);
        PoliticalPartyEntity savedEntity = politicalPartyRepository.save(entity);
        return modelMapper.map(savedEntity, ResponsePoliticalPartyDTO .class);
    }

    public List<ResponsePoliticalPartyDTO> getAll(String ideologia){
        List<PoliticalPartyEntity> partidos = politicalPartyRepository.findWithFilters(ideologia);
        return partidos.stream().map(politicalPartyEntity -> modelMapper.map(
                politicalPartyEntity, ResponsePoliticalPartyDTO.class)).collect(Collectors.toList());
    }

    public ResponsePoliticalPartyDTO getById(Long id){
        PoliticalPartyEntity politicalPartyEntity = politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        return modelMapper.map(politicalPartyEntity, ResponsePoliticalPartyDTO.class);
    }

    public List<ResponseAssociateDTO> getAllAssociates(Long partidoId){
        PoliticalPartyEntity politicalPartyEntity = politicalPartyRepository.findById(partidoId).orElseThrow(PoliticalPartyNotFoundException::new);
        List<AssociateEntity> associados = politicalPartyEntity.getAssociados();
        return associados.stream().map(associateEntity -> modelMapper.map(
                associateEntity, ResponseAssociateDTO.class)).collect(Collectors.toList());
    }

    public void delete(Long id){
        PoliticalPartyEntity politicalPartyEntity = politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        politicalPartyEntity.getAssociados().forEach(associateEntity -> {
            associateEntity.setPartido(null);
        });
        politicalPartyRepository.deleteById(id);
    }

    public void update (RequestPoliticalPartyDTO politicalPartyDTO, Long id){
        validationServicePoliticalParty.validateIdeologia(politicalPartyDTO);
        PoliticalPartyEntity politicalPartyEntity = politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        modelMapper.map(politicalPartyDTO, politicalPartyEntity);
        politicalPartyRepository.save(politicalPartyEntity);
    }
}
