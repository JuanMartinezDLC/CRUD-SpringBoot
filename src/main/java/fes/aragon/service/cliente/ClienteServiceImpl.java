package fes.aragon.service.cliente;

import fes.aragon.model.ClienteEntity;
import fes.aragon.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public void guardar(ClienteEntity cliente) {
         clienteRepository.save(cliente);
    }

    @Override
    public Page<ClienteEntity> listaCompleta(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public void borrar(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteEntity buscarClienteId(Integer id) {
        Optional<ClienteEntity> p=clienteRepository.findById(id);
        return p.orElse(null);
    }
}
