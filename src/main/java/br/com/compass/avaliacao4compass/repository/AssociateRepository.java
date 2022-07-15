package br.com.compass.avaliacao4compass.repository;

import br.com.compass.avaliacao4compass.entity.AssociateEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

    @Query("SELECT associate FROM AssociateEntity associate WHERE (:cargoPolitico IS NULL OR :cargoPolitico = associate.cargoPolitico)")
    public List<AssociateEntity> findWithFilters(@Param("cargoPolitico") String cargoPolitico, Sort sortBy);
}
