package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FournisseurMvtService {
    FournisseurCmd saveFournisseurCmd(FournisseurCmd fournisseurCmd);

    FournisseurRsp saveFournisseurRsp(FournisseurRsp fournisseurRsp);

    FournisseurFct saveFournisseurFct(FournisseurFct fournisseurFct);

    FournisseurFct getFournisseurFctById(int id);

    List<FournisseurFct> getFournisseurFctList();

    Page<FournisseurFct> getFournisseurFctPages(int page, int size);
}
