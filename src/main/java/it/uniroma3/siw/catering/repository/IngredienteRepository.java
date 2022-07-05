package it.uniroma3.siw.catering.repository;

import it.uniroma3.siw.catering.model.Ingrediente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
}
