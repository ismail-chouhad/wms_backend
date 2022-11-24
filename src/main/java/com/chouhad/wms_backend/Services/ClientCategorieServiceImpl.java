package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.ClientCategorie;
import com.chouhad.wms_backend.Repositories.ClientCategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class ClientCategorieServiceImpl implements ClientCategorieService {
    ClientCategorieRepository clientCategorieRepository;
    @Override
    public ClientCategorie saveClientCategorie(ClientCategorie clientCategorie) {
        return clientCategorieRepository.save(clientCategorie);
    }
    @Override
    public String getClientCategorieNameById(int id){
        return clientCategorieRepository.findClientCategorieNameById(id).getName();
    }
    @Override
    public List<ClientCategorie> getClientCategorieList(){
        return clientCategorieRepository.findAll();
    }
    @Override
    public Page<ClientCategorie> getCategoriesPages(int page, int size){
        return clientCategorieRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public ClientCategorie getClientCategorieByName(String name){
        return clientCategorieRepository.findClientCategorieByName(name);
    }
    @Override
    public ClientCategorie getClientCategorieById(int id){
        return clientCategorieRepository.findClientCategorieById(id);
    }
    @Override
    public void deleteClientCategorie(int id){
        clientCategorieRepository.deleteClientCategorieById(id);
    }
}

