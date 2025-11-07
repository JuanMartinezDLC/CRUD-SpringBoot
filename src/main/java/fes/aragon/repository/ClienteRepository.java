package fes.aragon.repository;

import fes.aragon.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer> {
    @Query("select p from cliente p where p.nombre like %?1%")
    List<ClienteEntity> buscarClientes(String dato);

}
