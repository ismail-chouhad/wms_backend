package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Fournisseur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FournisseurService {
    Fournisseur saveFournisseur(Fournisseur fournisseur);

    List<Fournisseur> getFournisseurList();

    Page<Fournisseur> getFournisseurPages(int page, int size);

    Fournisseur getFournisseurByAttributes(String name, String email, String num, String address);

    Fournisseur getFournisseurByName(String name);

    Fournisseur getFournisseurById(int id);

    void deleteFournisseur(int id);
}
