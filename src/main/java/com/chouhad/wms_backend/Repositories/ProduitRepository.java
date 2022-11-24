package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    Produit findProduitById(int Id);
    Produit findProduitByName(String name);
    String findProduitNameById(int id);
    List<Produit> findProduitsByName(String name);
    List<Produit> findProduitsByFamille(Famille famille);
    void deleteProduitById(int id);
}
