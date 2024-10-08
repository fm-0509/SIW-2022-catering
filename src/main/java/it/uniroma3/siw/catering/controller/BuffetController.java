package it.uniroma3.siw.catering.controller;

import it.uniroma3.siw.catering.Utils;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.CredentialService;
import it.uniroma3.siw.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static it.uniroma3.siw.catering.Utils.IsAdmin;

@Controller
public class BuffetController {


    @Autowired
    private BuffetService buffetService;
    @Autowired
    private ChefService chefService;
    @Autowired
    private PiattoService piattoService;


    @RequestMapping(value = {"/buffet","/admin/buffet"}, method = RequestMethod.GET)
    public String getAllBuffets(Model model)
    {
        model.addAttribute("buffets", this.buffetService.getAllBuffets());
        model.addAttribute("chefs", this.chefService.getAllChefs());
        return "buffet";
    }

    @RequestMapping(value = "buffet/{id}", method = RequestMethod.GET)
    public String getBuffetById(@ModelAttribute("id") Long id, Model model)
    {
        model.addAttribute("buffet", this.buffetService.findById(id));
        return "dettagliBuffet";
    }

    @RequestMapping(value = "/admin/addBuffet", method = RequestMethod.GET)
    public String addBuffetForm()
    {
        return "admin/addBuffetForm";
    }


    @RequestMapping(value = "admin/buffet", method = RequestMethod.POST)
    public String addBuffet(@Valid @ModelAttribute("buffet")Buffet buffet, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
    {
        if(! bindingResult.hasErrors())
        {
            this.buffetService.save(buffet);
            List<String> messages = new ArrayList<>();
            messages.add("Buffet "+buffet.getNome()+" inserito correttamente");
            redirectAttributes.addFlashAttribute("messages", messages);
            return "redirect:/admin/listaBuffets";
        }
        else
            return "/admin/addBuffetForm";

    }

    @RequestMapping(value = "/piatto/{id}")
    public String getPiatto(@ModelAttribute("id") Long id, Model model)
    {
        model.addAttribute("piatto", this.piattoService.getPiattoById(id));
        return "/dettagliPiatto";
    }




}
