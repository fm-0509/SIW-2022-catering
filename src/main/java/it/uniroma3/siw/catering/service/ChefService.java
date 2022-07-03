package it.uniroma3.siw.catering.service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import it.uniroma3.siw.catering.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public List<Chef> getAllBuffets()
    {
        List<Chef> chefs = new ArrayList<>();
        for(Chef b : this.chefRepository.findAll())
            chefs.add(b);

        return chefs;

    }

    public Chef findById(Long id) {
        return this.chefRepository.findById(id).orElse(null);
    }

    public List<Chef> getAllChefs() {
        ArrayList<Chef> chefs = new ArrayList<>();
        for(Chef c: this.chefRepository.findAll())
            chefs.add(c);

        return chefs;
    }
}
