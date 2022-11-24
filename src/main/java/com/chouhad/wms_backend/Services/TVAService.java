package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.TVA;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TVAService {
    TVA saveTVA(TVA tva);

    List<TVA> getTVAList();

    Page<TVA> getTVAsPages(int page, int size);

    TVA getTVAByTaux(float taux);

    TVA getTVAById(int id);

    void deleteTVA(int id);
}
