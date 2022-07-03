package it.uniroma3.siw.catering.service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    public List<Buffet> getAllBuffets()
    {
        List<Buffet> buffets = new ArrayList<>();
        for(Buffet b : this.buffetRepository.findAll())
            buffets.add(b);

        return buffets;

    }

    public Buffet findById(Long id) {
        return this.buffetRepository.findById(id).orElse(null);
    }
}
