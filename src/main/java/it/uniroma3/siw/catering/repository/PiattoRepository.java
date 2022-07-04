package it.uniroma3.siw.catering.repository;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
    List<Piatto> findPiattoByChef(Chef chef);

}
