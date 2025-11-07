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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "correo")
public class BajaCorreoController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("baja-correo")
    public String paginaBajaCorreo(@RequestParam(name = "page",
            defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,1);
        Page<ClienteEntity> clientes=clienteService.listaCompleta(pagReq);
        RenderPagina<ClienteEntity> render=new RenderPagina<>("baja-correo",clientes);
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",render);
        //busca físicamente una página
        return "correo/baja-correo";
    }
    @GetMapping("borrar-correo/{id}")
    public String borrarCliente(@PathVariable("id") Integer id,
                                Model model,
                                RedirectAttributes flash) {
        clienteService.borrar(id);
        flash.addFlashAttribute("success", "Cliente y correo se borro con éxito");
        //busca un end-point
        return "redirect:/correo/baja-correo";
    }
}
