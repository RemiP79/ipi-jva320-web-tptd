package com.ipi.jva320.web;

import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private SalarieAideADomicileService SalarieAideADomicileService;
@GetMapping(value = {"/","home.html"})
    public String home(final ModelMap model){
    model.put("message", "Bienvenue dans l'interface d'administration RH !");
    model.put("nbSalarie", SalarieAideADomicileService.countSalaries());
    return "home";
}
}
