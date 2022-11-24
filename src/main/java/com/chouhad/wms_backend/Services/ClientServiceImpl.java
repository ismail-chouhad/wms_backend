package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Client;
import com.chouhad.wms_backend.Entities.ClientCategorie;
import com.chouhad.wms_backend.Repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public List<Client> getClientList(){
        return clientRepository.findAll();
    }
    @Override
    public Page<Client> getClientPages(int page, int size){
        return clientRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Client getClientByAttributes(String name, String email, String num, String address){
        return clientRepository.findClientByNameAndEmailAndNumAndAddress(name,email,num,address);
    }
    @Override
    public Client getClientByName(String name){
        return clientRepository.findClientByName(name);
    }
    @Override
    public Client getClientById(int id){
        return clientRepository.findClientById(id);
    }
    @Override
    public void deleteClient(int id){
        clientRepository.deleteClientById(id);
    }
}

