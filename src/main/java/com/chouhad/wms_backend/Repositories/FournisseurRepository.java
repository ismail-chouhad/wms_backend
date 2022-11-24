package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Client;
import com.chouhad.wms_backend.Entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    Fournisseur findFournisseurById(int id);
    Fournisseur findFournisseurByName(String name);
    Fournisseur findFournisseurByNameAndEmailAndNumAndAddress(String name, String Email, String num, String address);
    void deleteFournisseurById(int id);
}
