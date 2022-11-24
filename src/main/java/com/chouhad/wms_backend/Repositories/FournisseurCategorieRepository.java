package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.FournisseurCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurCategorieRepository extends JpaRepository<FournisseurCategorie,Integer> {
    FournisseurCategorie findFournisseurCategorieById(int id);
    FournisseurCategorie findFournisseurCategorieByName(String name);
    FournisseurCategorie findFournisseurCategorieNameById(int id);
    void deleteFournisseurCategorieById(int id);
}
