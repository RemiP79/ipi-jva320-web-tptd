package com.ipi.jva320.web;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class PatientController {
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;
    @GetMapping("/salaries/{id}")
    public String getPatient(final ModelMap model, @PathVariable Long id) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        model.put("salarie", salarie);
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        return "/detail_Salarie";
    }
    @GetMapping("/salaries/aide/new")
    public String newForm(final ModelMap model) {
        model.put("empty", "");
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        return "/detail_Salarie";
    }
    @PostMapping("/salaries/save")
    public String creationSalarie(final ModelMap model, SalarieAideADomicile salarie) {
        try {
            salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        } catch (SalarieException e) {
           throw new RuntimeException(e);
        }
            //model.put("salarie", salarie);
            return "/detail_Salarie";

    }
    @PostMapping("/salaries/{id}")
    public String modificationSalarie(final ModelMap model, SalarieAideADomicile salarie) {
        try {
           salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        } catch (SalarieException e) {
            throw new RuntimeException(e);
        }
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        //model.put("salarie", salarie);
        return "/list";
    }
    @GetMapping("/salaries/{id}/delete")
    public String suppressionSalarie(final ModelMap model, @PathVariable Long id, SalarieAideADomicile salarie) {
        try {
            salarieAideADomicileService.deleteSalarieAideADomicile(id);
        } catch (SalarieException e) {
            throw new RuntimeException(e);
        }
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        return "/list";
    }
    @GetMapping(value="/salaries")
    public String faireLalisteDesSalaries(final ModelMap model){
        model.put("salaries", salarieAideADomicileService.getSalaries());
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        return "list";
    }
    @GetMapping("/salariés")
    public String rechercherSalaries(ModelMap model, @Nullable @RequestParam(name = "nom") String nom) throws SalarieException {
        List<SalarieAideADomicile> resultatsRecherche;
        model.put("nbSalarie", salarieAideADomicileService.countSalaries());
        if (nom != null && !nom.isEmpty()) {
           resultatsRecherche = salarieAideADomicileService.getSalaries(nom);
            if (resultatsRecherche.isEmpty()) {
                throw new SalarieException("Le nom n'est pas connu dans la base, retour page home : http://localhost:8080/");
            }
            model.put("salaries", resultatsRecherche);
            return "list";
        } else {
            return "list";
        }

    }
}
