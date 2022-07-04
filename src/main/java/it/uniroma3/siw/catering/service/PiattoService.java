package it.uniroma3.siw.catering.service;

import it.uniroma3.siw.catering.Utils;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static it.uniroma3.siw.catering.Utils.IterableToList;

@Service
public class PiattoService {
    @Autowired
    private PiattoRepository piattoRepository;

    public List<Piatto> getPiattiByChef(Chef chef)
    {
        return this.piattoRepository.findPiattoByChef(chef);
    }

    public List<Piatto> getAllPiatti()
    {
        return IterableToList(this.piattoRepository.findAll());
    }


    public Piatto getPiattoById(Long id) {
        return this.piattoRepository.findById(id).orElse(null);
    }

    public Piatto save(Piatto piatto)
    {
        return this.piattoRepository.save(piatto);
    }

    public boolean containsById(Long id) {
        return this.piattoRepository.existsById(id);
    }
}
