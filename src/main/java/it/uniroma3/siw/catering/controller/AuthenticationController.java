package it.uniroma3.siw.catering.controller;
import it.uniroma3.siw.catering.BootstrapAlert;
import it.uniroma3.siw.catering.model.security.Credential;
import it.uniroma3.siw.catering.model.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.catering.service.CredentialService;
import it.uniroma3.siw.catering.controller.validator.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthenticationController {
    @Autowired
    private CredentialService credentialsService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CredentialValidator credentialsValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm (Model model) {
        User u = new User();
        Credential c = new Credential();
        model.addAttribute("user", u);
        model.addAttribute("credential", c);
        return "registerUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm (Model model) {
        return "loginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "index";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credential credentials = credentialsService.getCredential(userDetails.getUsername());
        if (credentials.getRole().equals(Credential.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @ModelAttribute("credentials") Credential credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredential(credentials);
            return "registrationSuccessful";
        }
        model.addAttribute("credential", credentials);
        model.addAttribute("user", user);
        return "registerUser";
    }

    @RequestMapping("/logoutSuccess")
    public String logoutSuccess(Model model, RedirectAttributes redirectAttributes)
    {
       // this.messageSource.getMessage("logoutSuccessful", null, LocaleContextHolder.getLocale());
        redirectAttributes.addFlashAttribute("alert",BootstrapAlert.Success("Logout effettuato correttamente")); // un FlashAttribute viene aggiunto in automatico al Model dopo il redirect---
        return "redirect:/";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(Model model, RedirectAttributes redirectAttributes)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Login effettuato correttamente</strong> - Benvenuto "+userDetails.getUsername()));
        return "redirect:/";
    }

    @RequestMapping(value = "/loginError",method = RequestMethod.GET)
    public String loginError(Model model, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("alert",
                BootstrapAlert.Danger("<strong>ERRORE:</strong> Credenziali errate"));
        return "redirect:/";
    }

}
