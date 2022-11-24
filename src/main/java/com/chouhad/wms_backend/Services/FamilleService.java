package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Famille;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FamilleService {
    Famille saveFamille(Famille famille);

    String getFamilleNameById(int id);

    List<Famille> getFamilleList();

    Page<Famille> getFamillesPages(int page, int size);

    Famille getFamilleByName(String name);

    Famille getFamilleById(int id);

    void deleteFamille(int id);
}
