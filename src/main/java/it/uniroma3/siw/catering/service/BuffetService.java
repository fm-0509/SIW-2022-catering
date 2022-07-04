package it.uniroma3.siw.catering.service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static it.uniroma3.siw.catering.Utils.IterableToList;
@Component
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    public List<Buffet> getAllBuffets()
    {
        return IterableToList(this.buffetRepository.findAll());
    }

    public Buffet findById(Long id) {
        return this.buffetRepository.findById(id).orElse(null);
    }

    public Buffet save(Buffet buffet)
    {
        return this.buffetRepository.save(buffet);
    }

}


