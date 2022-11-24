package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.ClientCmd;
import com.chouhad.wms_backend.Entities.ClientFct;
import com.chouhad.wms_backend.Entities.ClientLvr;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientMvtService {
    ClientCmd saveClientCmd(ClientCmd fournisseurCmd);

    ClientCmd getClientCmdById(int id);

    Page<ClientCmd> getClientCmdPages(int page, int size);

    ClientLvr saveClientLvr(ClientLvr fournisseurLvr);

    ClientFct saveClientFct(ClientFct fournisseurFct);

    ClientFct getClientFctById(int id);

    List<ClientFct> getClientFctList();

    Page<ClientFct> getClientFctPages(int page, int size);
}
