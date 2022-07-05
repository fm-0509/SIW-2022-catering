package it.uniroma3.siw.catering.controller;


import it.uniroma3.siw.catering.JSONResponse;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.IngredienteService;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping(BASEPATH+"/buffets")
    public JSONResponse getAllBuffets()
    {
        return JSONResponse.CreateOKResponse(this.buffetService.getAllBuffets());
    }

    @GetMapping(BASEPATH+"/buffet/{id}")
    public JSONResponse getBuffet(@ModelAttribute(name = "id")Long id)
    {
        List<Buffet> buffet = new ArrayList<>();
        buffet.add(this.buffetService.getBuffetById(id));
        return JSONResponse.CreateOKResponse(buffet);
    }

    @RequestMapping(value = BASEPATH+"/buffet/{id}", method = RequestMethod.DELETE)
    public JSONResponse deleteBuffet(@ModelAttribute(name = "id") Long id)
    {
        if(this.buffetService.getBuffetById(id) == null)
            return JSONResponse.CreateErrorResponse("buffet inesistente");
        this.buffetService.delete(this.buffetService.getBuffetById(id));
            return JSONResponse.CreateOKResponse("");
    }

    @RequestMapping(value = BASEPATH+"/buffet", method = RequestMethod.POST)
    public JSONResponse addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            this.buffetService.save(buffet);
            return JSONResponse.CreateOKResponse("");
        }
        else return JSONResponse.CreateErrorResponse(bindingResult.getAllErrors());
    }

    @RequestMapping(value = BASEPATH+"/buffet", method = RequestMethod.PUT)
    public JSONResponse updateBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return JSONResponse.CreateErrorResponse(bindingResult.getAllErrors());

        if(buffet.getId() == null)
            return JSONResponse.CreateErrorResponse("");

        if(this.buffetService.getBuffetById(buffet.getId()) != null)
        {
            this.buffetService.save(buffet);
            return JSONResponse.CreateOKResponse("");
        }
    return JSONResponse.CreateErrorResponse("");
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


    @RequestMapping(method = RequestMethod.POST, value = BASEPATH+"/piatto")
    public JSONResponse addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return JSONResponse.CreateErrorResponse(bindingResult.getAllErrors());
        else {
            this.piattoService.save(piatto);
            Buffet buffet = piatto.getBuffet();
            buffet.getPiatti().add(piatto);
            this.buffetService.save(buffet);

        }
            return JSONResponse.CreateOKResponse("");


    }

    @RequestMapping(method = RequestMethod.DELETE, value = BASEPATH+"/piatto/{id}")
    public JSONResponse deletePiatto(@ModelAttribute("id") Long id)
    {
        this.piattoService.delete(this.piattoService.getPiattoById(id));
        return JSONResponse.CreateOKResponse("");
    }

    @RequestMapping(method = RequestMethod.POST, value = BASEPATH+"/ingrediente")
    public JSONResponse addIngrediente(@Valid @ModelAttribute("ingrediente")Ingrediente ingrediente , BindingResult bindingResult,
                                       @ModelAttribute("piatto") Long idPiatto)
    {
        if(bindingResult.hasErrors())
            return JSONResponse.CreateErrorResponse(bindingResult.getAllErrors());

        this.ingredienteService.save(ingrediente);
        Piatto piatto = this.piattoService.getPiattoById(idPiatto);
        piatto.getIngredienti().add(ingrediente);
        this.piattoService.save(piatto);
        return JSONResponse.CreateOKResponse("") ;

    }



}
