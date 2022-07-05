package it.uniroma3.siw.catering.repository;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;



    public void save(Ingrediente ingrediente) {
        this.ingredienteRepository.save(ingrediente);
    }
}
