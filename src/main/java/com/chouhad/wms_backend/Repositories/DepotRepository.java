package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotRepository extends JpaRepository<Depot,Integer> {
    Depot findDepotById(int id);
    Depot findDepotByNameAndAddress(String name, String address);
    Depot findDepotByName(String name);
    Depot findDepotNameById(int id);
    List<Depot> findDepotByVille(Ville ville);
    void deleteDepotById(int id);
}
