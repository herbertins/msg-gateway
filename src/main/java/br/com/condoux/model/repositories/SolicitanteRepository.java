package br.com.condoux.model.repositories;

import br.com.condoux.model.entities.Solicitante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends CrudRepository<Solicitante, Integer> {
}
