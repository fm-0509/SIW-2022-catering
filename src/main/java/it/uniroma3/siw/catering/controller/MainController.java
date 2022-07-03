package it.uniroma3.siw.catering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
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
