package it.uniroma3.siw.catering.repository;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import org.springframework.data.repository.CrudRepository;

public interface ChefRepository extends CrudRepository<Chef, Long> {
}
