package com.chouhad.wms_backend.Services;


import com.chouhad.wms_backend.Entities.Famille;
import com.chouhad.wms_backend.Entities.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {
    Produit getProduitById(int id);

    Produit getProduitByName(String name);

    String getProduitNameById(int id);
    List<Produit> getProduitsByFamille(Famille famille);

    List<Produit> getProduitsByName(String name);
    Page<Produit> getProduits(int page, int size);
    List<Produit> getListProduits();
    Produit saveProduit(Produit produit);

    void deleteProduitById(int id);
}
