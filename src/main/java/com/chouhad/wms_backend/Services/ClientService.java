package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Client;
import com.chouhad.wms_backend.Entities.ClientCategorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    Client saveClient(Client client);

    List<Client> getClientList();

    Page<Client> getClientPages(int page, int size);

    Client getClientByAttributes(String name, String email, String num, String address);

    Client getClientByName(String name);

    Client getClientById(int id);

    void deleteClient(int id);
}
