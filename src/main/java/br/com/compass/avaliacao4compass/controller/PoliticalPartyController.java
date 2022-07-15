package br.com.compass.avaliacao4compass.controller;

import br.com.compass.avaliacao4compass.dto.request.RequestPoliticalPartyDTO;
import br.com.compass.avaliacao4compass.dto.response.ResponsePoliticalPartyDTO;
import br.com.compass.avaliacao4compass.service.PoliticalPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PoliticalPartyController {

    @Autowired
    private PoliticalPartyService politicalPartyService;

    @PostMapping
    public ResponseEntity<ResponsePoliticalPartyDTO> addPoliticalParty (@RequestBody @Valid RequestPoliticalPartyDTO politicalPartyDTO, UriComponentsBuilder uriComponentsBuilder){
        ResponsePoliticalPartyDTO save = politicalPartyService.save(politicalPartyDTO, uriComponentsBuilder);
        URI uri = uriComponentsBuilder.path("partidos/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePoliticalPartyDTO>> listAllPoliticalParties(@RequestParam(required = false) String ideologia){
        List<ResponsePoliticalPartyDTO> response = politicalPartyService.getAll(ideologia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePoliticalPartyDTO> getPoliticalParty(@PathVariable Long id){
        ResponsePoliticalPartyDTO response = politicalPartyService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePoliticalParty(@RequestBody @Valid RequestPoliticalPartyDTO politicalPartyDTO, @PathVariable Long id){
        politicalPartyService.update(politicalPartyDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoliticalParty(@PathVariable Long id){
        politicalPartyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
