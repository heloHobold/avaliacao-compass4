package br.com.compass.avaliacao4compass.service;

import br.com.compass.avaliacao4compass.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4compass.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4compass.entity.AssociateEntity;
import br.com.compass.avaliacao4compass.exception.associate.AssociateNotFoundException;
import br.com.compass.avaliacao4compass.repository.AssociateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private ValidationServiceAssociate validationServiceAssociate;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseAssociateDTO save(RequestAssociateDTO associateDTO, UriComponentsBuilder uriComponentsBuilder){
        validationServiceAssociate.validateCargoPolitico(associateDTO) ;
        validationServiceAssociate.validateSexo(associateDTO);
        AssociateEntity entity = modelMapper.map(associateDTO, AssociateEntity.class);
        AssociateEntity savedEntity = associateRepository.save(entity);
        return modelMapper.map(savedEntity, ResponseAssociateDTO.class);
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

    public void delete(Long id){
        associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        associateRepository.deleteById(id);
    }

    public void update (RequestAssociateDTO associateDTO, Long id){
        validationServiceAssociate.validateCargoPolitico(associateDTO);
        validationServiceAssociate.validateSexo(associateDTO);
        AssociateEntity associateEntity = associateRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        modelMapper.map(associateDTO, associateEntity);
        associateRepository.save(associateEntity);
    }
}

