package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Pallete;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PalleteService {
    Pallete savePallete(Pallete pallete);

    List<Pallete> getPalleteList();

    Page<Pallete> getPalletePages(int page, int size);

    Pallete getPalleteByAttributes(String name, String numSerie, long quantity);

    Pallete getPalleteByName(String name);

    Pallete getPalleteById(int id);

    void deletePallete(int id);
}
