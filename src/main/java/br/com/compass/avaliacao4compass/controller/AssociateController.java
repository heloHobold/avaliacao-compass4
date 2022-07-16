package br.com.compass.avaliacao4compass.controller;

import br.com.compass.avaliacao4compass.dto.request.RequestAssociateDTO;
import br.com.compass.avaliacao4compass.dto.request.RequestAssociateToPoliticalParty;
import br.com.compass.avaliacao4compass.dto.response.ResponseAssociateDTO;
import br.com.compass.avaliacao4compass.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/associados")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @PostMapping
    public ResponseEntity<ResponseAssociateDTO> addAssociate (@RequestBody @Valid RequestAssociateDTO associateDTO, UriComponentsBuilder uriComponentsBuilder){
        ResponseAssociateDTO save = associateService.save(associateDTO);
        URI uri = uriComponentsBuilder.path("associados/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> addAssociateToPoliticalParty (@RequestBody @Valid RequestAssociateToPoliticalParty request){
        associateService.addAssociateToPoliticalParty(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseAssociateDTO>> listAllAssociates(@RequestParam(required = false) String cargoPolitico,
            @RequestParam(required = false, defaultValue = "id") String sortBy){
        List<ResponseAssociateDTO> response = associateService.getAll(cargoPolitico, sortBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociateDTO> getAssociate(@PathVariable Long id){
        ResponseAssociateDTO response = associateService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAssociate(@RequestBody @Valid RequestAssociateDTO associateDTO, @PathVariable Long id){
        associateService.update(associateDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociate(@PathVariable Long id){
        associateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idAssociate}/partidos/{idPoliticalParty}")
    public ResponseEntity<Void> deleteAssociateByPoliticalParty(@PathVariable Long idAssociate, @PathVariable Long idPoliticalParty){
        associateService.deleteByPoliticalParty(idAssociate, idPoliticalParty);
        return ResponseEntity.noContent().build();
    }
}
