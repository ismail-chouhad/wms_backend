package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.FournisseurCategorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FournisseurCategorieService {
    FournisseurCategorie saveFournisseurCategorie(FournisseurCategorie fournisseurCategorie);

    String getFournisseurCategorieNameById(int id);

    List<FournisseurCategorie> getFournisseurCategorieList();

    Page<FournisseurCategorie> getCategoriesPages(int page, int size);

    FournisseurCategorie getFournisseurCategorieByName(String name);

    FournisseurCategorie getFournisseurCategorieById(int id);

    void deleteFournisseurCategorie(int id);
}
