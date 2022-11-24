package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalleteRepository extends JpaRepository<Pallete,Integer> {
    Pallete findPalleteById(int id);
    Pallete findPalleteByName(String name);

    Pallete findPalleteByNameAndNumSerieAndQuantity(String name, String numSerie, long quantity);
    Pallete findPalleteByEmplacement(Emplacement emplacement);
    Pallete findPalleteByProduit(Produit produit);
    Pallete findPalleteByClientCmd(ClientCmd clientCmd);
    Pallete findPalleteByFournisseurCmd(FournisseurCmd fournisseurCmd);
    void deletePalleteById(int id);
}
