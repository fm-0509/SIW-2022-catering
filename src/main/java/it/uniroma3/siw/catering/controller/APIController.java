package it.uniroma3.siw.catering.controller;


import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class APIController {

    static final String BASEPATH = "/api";

    @Autowired
    private BuffetService buffetService;
    @Autowired
    private ChefService chefService;

    @GetMapping("/buffets")
    public List<Buffet> getAllBuffets()
    {
        return this.buffetService.getAllBuffets();
    }

    @GetMapping("/buffet/{id}")
    public Buffet getBuffet(@ModelAttribute(name = "id")Long id)
    {
        return this.buffetService.findById(id);
    }

    @GetMapping("/chefs")
    public List<Chef> getAllChefs()
    {
        return this.chefService.getAllChefs();
    }

    @GetMapping("/chef/{id}")
    public Chef getChef(@ModelAttribute(name = "id")Long id)
    {
        return this.chefService.findById(id);
    }




}
