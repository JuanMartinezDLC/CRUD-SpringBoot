package fes.aragon.service.cliente;

import fes.aragon.model.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    void guardar(ClienteEntity cliente);
    Page<ClienteEntity> listaCompleta(Pageable pageable);
    void borrar(Integer id);
    ClienteEntity buscarClienteId(Integer id);
}
