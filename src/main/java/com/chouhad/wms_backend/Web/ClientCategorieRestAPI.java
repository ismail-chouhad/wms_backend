package com.chouhad.wms_backend.Web;

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

public class ClientCategorieRestAPI {
    private ClientCategorieService clientCategorieService;
    private ClientService clientService;

    @GetMapping("/clientCategories")
    public List<ClientCategorie> clientCategorieList() {
        return clientCategorieService.getClientCategorieList();
    }
    @GetMapping("/clientCategories/{id}")
    public ClientCategorie getClientCategorie(@PathVariable int id){
        return clientCategorieService.getClientCategorieById(id);
    }

    @GetMapping("/clientCategories/getId/{name}")
    public int getClientCategorieByName(@PathVariable String name){
        ClientCategorie clientCategorieByName = clientCategorieService.getClientCategorieByName(name);
        if(clientCategorieByName != null) {
            return clientCategorieByName.getId();
        }
        return -1;
    }

    @GetMapping("/clientCategories/{page}/{size}")
    public List<String> getClientCategorie(@PathVariable int page, @PathVariable int size){
        Page<ClientCategorie> clientCategoriePage = clientCategorieService.getCategoriesPages(page,size);
        List<String> clientCategoriesName= new ArrayList<>();
        clientCategoriePage.forEach(cCP-> clientCategoriesName.add(cCP.getName()));
        return clientCategoriesName;
    }
    @GetMapping("/clientCategories/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return clientCategorieService.getCategoriesPages(page,size).getTotalPages();
    }
    @GetMapping("/clientCategories/getAllCategoriesNames")
    public List<String> getListCategoriesNames(){
        List<ClientCategorie> clientCategorieList = clientCategorieService.getClientCategorieList();
        List<String> listNames = new ArrayList<>();
        clientCategorieList.forEach(categorieClient ->  {listNames.add(categorieClient.getName());});
        return listNames;
    }
    @PostMapping("/clientCategories/save/{name}")
    public String addClientCategorie(@PathVariable String name){
        ClientCategorie clientCategorie = clientCategorieService.getClientCategorieByName(name);
        if(clientCategorie == null){
            ClientCategorie clientCategorie1 = new ClientCategorie();
            clientCategorie1.setName(name);
            clientCategorieService.saveClientCategorie(clientCategorie1);
            return "Catégorie ajoutée";
        }
        return "Catégorie existe déjà";
    }

    @PutMapping("/clientCategories/edit/{id}/{name}")
    public String updateClientCategorie(@PathVariable int id, @PathVariable String name){
        ClientCategorie clientCategorie = clientCategorieService.getClientCategorieById(id);
        ClientCategorie clientCategorieByName = clientCategorieService.getClientCategorieByName(name);
        if(clientCategorie != null && clientCategorieByName == null){
            clientCategorie.setName(name);
            clientCategorieService.saveClientCategorie(clientCategorie);
            return "Catégorie modifiée";
        }
        return "Catégorie non modifiée";
    }
    @DeleteMapping("/clientCategories/delete/{id}")
    public void deleteClientCategorie(@PathVariable int id){
        clientCategorieService.deleteClientCategorie(id);
    }


}
