package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Client;
import com.chouhad.wms_backend.Entities.ClientCategorie;
import com.chouhad.wms_backend.Services.ClientCategorieService;
import com.chouhad.wms_backend.Services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class ClientRestAPI {
    private ClientService clientService;
    private ClientCategorieService clientCategorieService;

    @PostMapping("/clients/save/{name}/{email}/{num}/{address}/{id}")
    public String postClient(@PathVariable String name,@PathVariable String email,
                            @PathVariable String num,@PathVariable String address,@PathVariable int id){
        ClientCategorie clientCategorieById = clientCategorieService.getClientCategorieById(id);
        Client client = clientService.getClientByAttributes(name,email,num,address);
        if(client == null)
        {
            Client client1 = new Client();
            client1.setName(name);
            client1.setEmail(email);
            client1.setNum(num);
            client1.setAddress(address);
            client1.setClientCategorie(clientCategorieById);
            clientService.saveClient(client1);
            return "Client ajoute";
        }
        return "Client existe deja";
    }
    @GetMapping("/clients/{page}/{size}")
    public List<List<String>> getPageClients(@PathVariable int page, @PathVariable int size){
        Page<Client> clientPage = clientService.getClientPages(page,size);
        List<List<String>> list = new ArrayList<>();
        clientPage.forEach(client -> { list.add(client.getInfo()); });
        return list;
    }
    @GetMapping("/clients")
    public List<Client> clientList() {
        return clientService.getClientList();
    }
    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable int id){
        return clientService.getClientById(id);
    }
    @GetMapping("/clients/getId/{name}")
    public int getClientByName(@PathVariable String name){
        Client clientByName = clientService.getClientByName(name);
        if(clientByName != null) {
            return clientByName.getId();
        }
        return -1;
    }
    @GetMapping("/clients/names/{page}/{size}")
    public List<String> getClientsNames(@PathVariable int page, @PathVariable int size){
        Page<Client> clientPage = clientService.getClientPages(page,size);
        List<String> clientsNames= new ArrayList<>();
        clientPage.forEach(cCP-> clientsNames.add(cCP.getName()));
        return clientsNames;
    }
    @GetMapping("/clients/getAllClientsNames")
    public List<String> getClientsNamesList(){
        List<Client> clientPage = clientService.getClientList();
        List<String> clientsNames= new ArrayList<>();
        clientPage.forEach(cCP-> clientsNames.add(cCP.getName()));
        return clientsNames;
    }
    @GetMapping("/clients/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return clientService.getClientPages(page,size).getTotalPages();
    }
    @PutMapping("/clients/edit/{id}/{name}/{email}/{num}/{address}/{CategorieId}")
    public String updateClient(@PathVariable int id, @PathVariable String name,
                               @PathVariable String email, @PathVariable String num,
                               @PathVariable String address, @PathVariable int CategorieId){
        ClientCategorie clientCategorie = clientCategorieService.getClientCategorieById(CategorieId);
        Client client = clientService.getClientById(id);
        Client clientByAttributes = clientService.getClientByAttributes(name,email,num,address);
        if(client != null && clientByAttributes == null){
            client.setName(name);
            client.setEmail(email);
            client.setNum(num);
            client.setAddress(address);
            clientCategorie.setName(clientCategorieService.getClientCategorieNameById(CategorieId));
            client.setClientCategorie(clientCategorie);
            clientService.saveClient(client);
            return "Client modifié";
        }
        return "Client non modifié";
    }
    @DeleteMapping("/clients/delete/{id}")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }
}
