package com.ipi.jva320.web;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller

public class PatientController {
    @Autowired
    private SalarieAideADomicileService SalarieAideADomicileService;


    @GetMapping("/salaries/{id}")
    public String getPatient(final ModelMap model, @PathVariable Long id) {
        SalarieAideADomicile salarie = SalarieAideADomicileService.getSalarie(id);
        model.put("salarie", salarie);
        return "/detail_Salarie";
    }


    @GetMapping("/salaries/aide/new")
    public String newForm(final ModelMap model) {
        model.put("empty", "");
        return "/detail_Salarie";
    }

    @PostMapping("/salaries/aide/10")
    public String creationSalarie(final ModelMap model, SalarieAideADomicile salarie) {
        try {
            SalarieAideADomicileService.creerSalarieAideADomicile(salarie);
        } catch (SalarieException e) {
            throw new RuntimeException(e);
        }
        //model.put("salarie", salarie);
        return "/detail_Salarie";
    }

    @PostMapping("/salaries/{id}")
    public String modificationSalarie(final ModelMap model, SalarieAideADomicile salarie) {
        try {
           SalarieAideADomicileService.updateSalarieAideADomicile(salarie);
        } catch (SalarieException e) {
            throw new RuntimeException(e);
        }
        //model.put("salarie", salarie);
        return "redirect:/list.html";
    }
    @GetMapping("/salaries/{id}/delete")
    public String suppressionSalarie(final ModelMap model, @PathVariable Long id, SalarieAideADomicile salarie) {
        try {
            SalarieAideADomicileService.deleteSalarieAideADomicile(id);
        } catch (SalarieException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/list.html";
    }

    @GetMapping(value="/salaries")
    public String faireLalisteDesSalaries(final ModelMap model){
        model.put("salaries", SalarieAideADomicileService.getSalaries());
        return "list";
    }








        @GetMapping ("/salariés?nom={nom}")
        public String listerSalaries(ModelMap model,@Nullable @RequestParam(name = "nom") String nom) {
            List<SalarieAideADomicile> resultatsRecherche;

            if ( SalarieAideADomicileService.getSalaries(nom).isEmpty() &&  nom != null && !nom.isEmpty()) {
                // Effectuer la recherche par nom
                throw new RuntimeException();
               //resultatsRecherche = SalarieAideADomicileService.getSalaries();
            }
            else {
               // Obtenir tous les salariés si aucun nom n'est spécifié
                resultatsRecherche = null ;///////////////////////////////
            }

            // Ajouter les résultats à la vue
            model.put("resultatsRecherche", resultatsRecherche);

            return "/salaries/{id}";
        }





}


  /*  @GetMapping("/salaries/aide/11/edit")
    public String editSalarie(@PathVariable Long id, Model model) {
        SalarieAideADomicile salarie = SalarieAideADomicileService.getSalarie(id);

        // Activez le champ id pour le formulaire d'édition
        salarie.setDisable(false);

        model.addAttribute("salarie", salarie);
        return "/detail_Salarie";
    }*/
