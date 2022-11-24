package com.chouhad.wms_backend.Repositories;


import com.chouhad.wms_backend.Entities.FournisseurFct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurFctRepository extends JpaRepository<FournisseurFct,Integer> {
    FournisseurFct findFournisseurFctById(int id);
}
