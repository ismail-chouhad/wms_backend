package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmplacementRepository extends JpaRepository<Emplacement,Integer> {
    Emplacement findEmplacementById(int id);
    List<Emplacement> findEmplacementByDepot(Depot depot);
    Emplacement findEmplacementByAlleeAndNvHorizontalAndNvVerticalAndDepot(String allee,
                                                                   String nvHorizontal,
                                                                   String nvVertical,
                                                                   Depot depot);

    void deleteEmplacementById(int id);
}
