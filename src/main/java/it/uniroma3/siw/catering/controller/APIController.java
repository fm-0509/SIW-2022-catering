package it.uniroma3.siw.catering.controller;


import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController("/api")
public class APIController {

    static final String BASEPATH = "/api";

    @Autowired
    private BuffetService buffetService;
    @Autowired
    private ChefService chefService;

    @Autowired
    private PiattoService piattoService;

    @GetMapping(BASEPATH+"/buffets")
    public List<Buffet> getAllBuffets()
    {
        return this.buffetService.getAllBuffets();
    }

    @GetMapping(BASEPATH+"/buffet/{id}")
    public Buffet getBuffet(@ModelAttribute(name = "id")Long id)
    {
        return this.buffetService.findById(id);
    }

    @GetMapping(BASEPATH+"/chefs")
    public List<Chef> getAllChefs()
    {
        return this.chefService.getAllChefs();
    }

    @GetMapping(BASEPATH+"/chef/{id}")
    public Chef getChef(@ModelAttribute(name = "id")Long id)
    {
        return this.chefService.findById(id);
    }

    @GetMapping(BASEPATH+"/piatti/by-chef/{idChef}")
    public List<Piatto> getPiattiByChef(@ModelAttribute(name = "idChef")Long idChef)
    {
        Chef chef = this.chefService.findById(idChef);
        if(chef == null)
            return Collections.emptyList();
        return this.piattoService.getPiattiByChef(chef);
    }

    @GetMapping(BASEPATH+"/piatti/by-id/{$id}")
    public Piatto getPiattoById(@ModelAttribute("id") Long id)
    {
        return this.piattoService.getPiattoById(id);
    }

    @RequestMapping(value = BASEPATH+"/piatti", method = RequestMethod.PUT)
    public Piatto updatePiatto(@ModelAttribute("piatto") Piatto piatto)
    {
        if(! this.piattoService.containsById(piatto.getId()))
            return null;
        else
            return this.piattoService.save(piatto);
    }




}
