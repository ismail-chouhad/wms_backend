package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Famille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilleRepository extends JpaRepository<Famille,Integer> {
    Famille findFamilleById(int id);
    Famille findFamilleNameById(int id);
    Famille findFamilleByName(String name);
    void deleteFamilleById(int id);
}
