package it.uniroma3.siw.catering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"} )
    public String defMethod(Model model)
    {
        return "index";
    }



    @RequestMapping("/test")
    public String test()
    {
        return "test";
    }
}
