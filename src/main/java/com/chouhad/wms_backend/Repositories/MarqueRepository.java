package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarqueRepository extends JpaRepository<Marque,Integer> {
    Marque findMarqueById(int id);
    Marque findMarqueByName(String name);
    Marque findMarqueNameById(int id);
    void deleteMarqueById(int id);
}
