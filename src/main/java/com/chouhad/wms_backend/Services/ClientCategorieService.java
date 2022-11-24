package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.ClientCategorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientCategorieService {
    ClientCategorie saveClientCategorie(ClientCategorie clientCategorie);

    String getClientCategorieNameById(int id);

    List<ClientCategorie> getClientCategorieList();

    Page<ClientCategorie> getCategoriesPages(int page, int size);

    ClientCategorie getClientCategorieByName(String name);

    ClientCategorie getClientCategorieById(int id);

    void deleteClientCategorie(int id);
}
