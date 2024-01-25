package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Planets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanetsController {
    @GetMapping("/planets")
    public String displayIndex(Model model) {
        model.addAttribute("planets", Planets.values());
        return "planets/index";
    }


}
