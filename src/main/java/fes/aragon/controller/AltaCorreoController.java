package fes.aragon.controller;

import fes.aragon.model.ClienteEntity;
import fes.aragon.service.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="correo")
public class  AltaCorreoController {
    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "salvar-cliente",method = RequestMethod.POST)
    public String salvarCliente(@RequestParam("nombre")String nombre,
                                @RequestParam("id_tipo")String tipo){
        System.out.println(nombre);
        System.out.println(tipo);
        return "correo/alta-correo";
    }
    @PostMapping("salvar-cliente-dos")
    public String salvarClienteDos(@Valid @ModelAttribute("cliente")ClienteEntity cliente,
                                   BindingResult resultado, Model model){
        //Validar si hay error
        if(resultado.hasErrors()){
            model.addAttribute("success","Revisa los campos ");
            return "correo/alta-correo";
        }
        //almacenar en la B.D
        clienteService.guardar(cliente);
        model.addAttribute("success","Se almaceno el cliente con éxito");
        return "correo/alta-correo";
    }
}
