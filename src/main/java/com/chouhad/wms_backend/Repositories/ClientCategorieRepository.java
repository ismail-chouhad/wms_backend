package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.ClientCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCategorieRepository extends JpaRepository<ClientCategorie,Integer> {
    ClientCategorie findClientCategorieById(int id);
    ClientCategorie findClientCategorieNameById(int id);
    ClientCategorie findClientCategorieByName(String name);
    ClientCategorie findClientCategorieByClientsId(int id);
    void deleteClientCategorieById(int id);
}
