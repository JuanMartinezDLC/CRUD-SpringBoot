package fes.aragon.controller;

import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class InicioController {
    @GetMapping("/")
    public String inicioPagina(Model model){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("mensaje","Hola Spring Web");
        model.addAttribute("fecha",format.format(new Date()));

        return "index";
    }
    @GetMapping("principal")
    public String principalPagina(Model model){
        model.addAttribute("mensaje","Página de principal");
        model.addAttribute("success","El sistema se inicio");
        model.addAttribute("warning","Peligro alumnos realizan el sistema");
        return "principal";
    }
}
