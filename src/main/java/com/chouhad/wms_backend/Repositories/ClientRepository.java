package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findClientById(int id);
    Client findClientByName(String name);
    Client findClientByNameAndEmailAndNumAndAddress(String name, String email, String num, String address);
    void deleteClientById(int id);
}
