package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville,Integer> {
    Ville findVilleById(int id);
    Ville findVilleNameById(int id);
    Ville findVilleByName(String name);
    void deleteVilleById(int id);
}
