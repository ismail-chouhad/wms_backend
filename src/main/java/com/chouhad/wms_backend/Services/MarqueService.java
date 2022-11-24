package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Marque;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MarqueService {
    Marque saveMarque(Marque marque);

    String getMarqueNameById(int id);

    List<Marque> getMarqueList();

    Page<Marque> getMarquesPages(int page, int size);

    Marque getMarqueByName(String name);

    Marque getMarqueById(int id);

    void deleteMarque(int id);
}
