package br.com.compass.avaliacao4compass.service;

import br.com.compass.avaliacao4compass.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4compass.dto.request.RequestAssociateToPoliticalParty;
import br.com.compass.avaliacao4compass.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4compass.entity.AssociateEntity;
import br.com.compass.avaliacao4compass.entity.PoliticalPartyEntity;
import br.com.compass.avaliacao4compass.exception.associate.AssociateNotLinkedException;
import br.com.compass.avaliacao4compass.exception.associate.AssociateAlreadyLinkedException;
import br.com.compass.avaliacao4compass.exception.associate.AssociateNotFoundException;
import br.com.compass.avaliacao4compass.exception.politicalParty.PoliticalPartyNotFoundException;
import br.com.compass.avaliacao4compass.repository.AssociateRepository;
import br.com.compass.avaliacao4compass.repository.PoliticalPartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;
    @Autowired
    private ValidationServiceAssociate validationServiceAssociate;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseAssociateDTO save(RequestAssociateDTO associateDTO){
        validationServiceAssociate.validateCargoPolitico(associateDTO) ;
        validationServiceAssociate.validateSexo(associateDTO);
        AssociateEntity entity = modelMapper.map(associateDTO, AssociateEntity.class);
        AssociateEntity savedEntity = associateRepository.save(entity);
        return modelMapper.map(savedEntity, ResponseAssociateDTO.class);
    }

  public void addAssociateToPoliticalParty(RequestAssociateToPoliticalParty request) {
        Long idAssociate = request.getIdAssociado();
        associateRepository.findById(idAssociate).orElseThrow(AssociateNotFoundException::new);
        Long idPoliticalParty = request.getIdPartido();
        associateRepository.findById(idPoliticalParty).orElseThrow(PoliticalPartyNotFoundException::new);

        AssociateEntity entity = associateRepository.getReferenceById(idAssociate);

        if (entity.getPartido() == null){
            PoliticalPartyEntity referenceById = politicalPartyRepository.getReferenceById(idPoliticalParty);
            entity.setPartido(referenceById);
            associateRepository.save(entity);
        } else {
            throw new AssociateAlreadyLinkedException();
        }
    }

    public List<ResponseAssociateDTO> getAll(String cargoPolitico, String sortBy){
        List<AssociateEntity> associates = associateRepository.findWithFilters(cargoPolitico, Sort.by(Sort.Direction.ASC, sortBy));
        return associates.stream().map(associateEntity -> modelMapper.map(
                associateEntity, ResponseAssociateDTO.class)).collect(Collectors.toList());
    }

    public ResponseAssociateDTO getById(Long id){
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        return modelMapper.map(associateEntity, ResponseAssociateDTO.class);
    }

    public void update (RequestAssociateDTO associateDTO, Long id){
        validationServiceAssociate.validateCargoPolitico(associateDTO);
        validationServiceAssociate.validateSexo(associateDTO);
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        modelMapper.map(associateDTO, associateEntity);
        associateRepository.save(associateEntity);
    }

    public void delete(Long id){
        associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        associateRepository.deleteById(id);
    }

    public void deleteByPoliticalParty(Long idAssociate, Long idPoliticalParty){
        politicalPartyRepository.findById(idPoliticalParty).orElseThrow(PoliticalPartyNotFoundException::new);
        associateRepository.findById(idAssociate).orElseThrow(AssociateNotFoundException::new);

        AssociateEntity referenceByIdAssociate = associateRepository.getReferenceById(idAssociate);
        PoliticalPartyEntity referenceByIdPoliticalParty = politicalPartyRepository.getReferenceById(idPoliticalParty);
        boolean equals = referenceByIdAssociate.getPartido().equals(referenceByIdPoliticalParty);

        if (equals){
            referenceByIdAssociate.setPartido(null);
            associateRepository.save(referenceByIdAssociate);
        } else {
            throw new AssociateNotLinkedException();
        }

    }

}

