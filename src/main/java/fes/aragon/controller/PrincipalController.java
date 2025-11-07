package fes.aragon.controller;


import fes.aragon.model.ClienteEntity;
import fes.aragon.service.cliente.ClienteService;
import fes.aragon.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="principal")
public class PrincipalController {

    @GetMapping("alta-correo")
    public String paginaAltaCorreo(Model model){
        ClienteEntity cliente=new ClienteEntity();
        model.addAttribute("cliente",cliente);
        return "correo/alta-correo";
    }

    @GetMapping("lista-correo")
    public String paginaListaCorreo(Model model){
        return "correo/lista-correo";
    }
    @GetMapping("mandar-correo")
    public String paginaMandarCorreo(Model model){
        return "correo/mandar-correo";
    }

}
