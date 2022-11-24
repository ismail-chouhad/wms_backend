package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Ville;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VilleService {
    Ville saveVille(Ville ville);

    String getVilleNameById(int id);

    List<Ville> getVilleList();

    Page<Ville> getVillesPages(int page, int size);

    Ville getVilleByName(String name);

    Ville getVilleById(int id);

    void deleteVille(int id);
}
