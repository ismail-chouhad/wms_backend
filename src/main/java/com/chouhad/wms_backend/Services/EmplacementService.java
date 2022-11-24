package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Emplacement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmplacementService {
    Emplacement saveEmplacement(Emplacement emplacement);

    Emplacement getEmplacementByAttributes(String allee, String nvHorizontal, String nvVertical, Depot depot);

    List<Emplacement> getEmplacementList();

    Page<Emplacement> getEmplacementsPages(int page, int size);

    Emplacement getEmplacementById(int id);

    void deleteEmplacement(int id);
}
