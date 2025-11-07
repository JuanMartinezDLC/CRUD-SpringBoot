package fes.aragon.controller;

import fes.aragon.model.ClienteEntity;
import fes.aragon.service.cliente.ClienteService;
import fes.aragon.util.RenderPagina;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping(value = "correo")
public class ListaCorreoController {
    @Autowired
    ClienteService clienteService;
    @GetMapping("lista-correo")
    public String paginaBajaCorreo(@RequestParam(name = "page",
            defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,5);
        Page<ClienteEntity> clientes=clienteService.listaCompleta(pagReq);
        RenderPagina<ClienteEntity> render=new RenderPagina<>("lista-correo",clientes);
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",render);
        return "correo/lista-correo";
    }
    @GetMapping("mandar-correo/{id}")
    public String saltoModificar(@PathVariable("id") Integer id,
                                 Model model,
                                 RedirectAttributes flash) {
        ClienteEntity cliente = clienteService.buscarClienteId(id);
        gmail(cliente);
        flash.addFlashAttribute("success","Se mando el correo con éxito");
        return "redirect:/correo/lista-correo";
    }

    private void gmail(ClienteEntity cliente){
        //Agrega el correo y password de google
        String gmail="";
        String pswd="";
        Properties p=System.getProperties();
        p.setProperty("mail.smtps.host","smpt.gmail.com");
        p.setProperty("mail.smtps.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtps.socketFactory.fallback","false");
        p.setProperty("mail.smtp.port","465");
        p.setProperty("mail.smtp.socketFactory.port","465");
        p.setProperty("mail.smtps.auth","true");
        p.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
        p.setProperty("mail.smtps.ssl.trust","smtp.gmail.com");
        p.setProperty("mail.smtp.ssl.quitwait","false");
        SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        String cadena="<h2>Te mandamos un mensaje de prueba</h2>"
                +f.format(new Date())+"</br>";
        try{
            Session session=Session.getInstance(p,null);
            MimeMessage message=new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(cliente.getCorreo(),false));
            message.setSubject("Se mando");
            message.setContent(cadena,"text/html");
            message.setSentDate(new Date());
            Transport transport=(Transport)session.getTransport("smtps");
            transport.connect("smtp.gmail.com",gmail,pswd);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
