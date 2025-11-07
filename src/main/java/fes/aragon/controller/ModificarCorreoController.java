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

@Controller
@RequestMapping(value="correo")
public class ModificarCorreoController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("modificar-correo")
    public String paginaBajaCorreo(@RequestParam(name = "page",
            defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,1);
        Page<ClienteEntity> clientes=clienteService.listaCompleta(pagReq);
        RenderPagina<ClienteEntity> render=new RenderPagina<>("modificar-correo",clientes);
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",render);
        return "correo/modificacion-correo";
    }
    @GetMapping("modificar-cliente/{id}")
    public String saltoModificar(@PathVariable("id") Integer id, Model model) {
        ClienteEntity cliente = clienteService.buscarClienteId(id);
        model.addAttribute("cliente", cliente);
        return "correo/alta-correo";
    }
}
