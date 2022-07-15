package br.com.compass.avaliacao4compass.repository;

import br.com.compass.avaliacao4compass.entity.PoliticalPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalPartyEntity, Long> {
    @Query("SELECT politicalParty FROM PoliticalPartyEntity politicalParty WHERE (:ideologia IS NULL OR :ideologia = politicalParty.ideologia)")
    List<PoliticalPartyEntity> findWithFilters(String ideologia);
}
