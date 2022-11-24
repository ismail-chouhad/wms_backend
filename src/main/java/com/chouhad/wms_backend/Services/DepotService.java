package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepotService {
    Depot saveDepot(Depot depot);

    String getDepotNameById(int id);

    List<Depot> getDepotList();

    Page<Depot> getDepotsPages(int page, int size);

    Depot getDepotByNameAndAddress(String name, String address);

    Depot getDepotByName(String name);

    Depot getDepotById(int id);

    void deleteDepot(int id);
}
