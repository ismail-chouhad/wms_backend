package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.TVA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TVARepository extends JpaRepository<TVA,Integer> {
    TVA findTVAById(int id);
    TVA findTVAByTaux(float taux);
    TVA findTVATauxById(int id);
    void deleteTVAById(int id);
}
