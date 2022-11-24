package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.ClientCmd;
import com.chouhad.wms_backend.Entities.ClientFct;
import com.chouhad.wms_backend.Entities.ClientLvr;
import com.chouhad.wms_backend.Repositories.ClientCmdRepository;
import com.chouhad.wms_backend.Repositories.ClientFctRepository;
import com.chouhad.wms_backend.Repositories.ClientLvrRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class ClientMvtServiceImpl implements ClientMvtService {
    ClientCmdRepository clientCmdRepository;
    ClientLvrRepository clientLvrRepository;
    ClientFctRepository clientFctRepository;
    @Override
    public ClientCmd saveClientCmd(ClientCmd clientCmd) {
        return clientCmdRepository.save(clientCmd);
    }
    @Override
    public ClientCmd getClientCmdById(int id) {
        return clientCmdRepository.findClientCmdById(id);
    }
    @Override
    public Page<ClientCmd> getClientCmdPages(int page, int size) {
        return clientCmdRepository.findAll(PageRequest.of(page , size));
    }
    @Override
    public ClientLvr saveClientLvr(ClientLvr clientLvr) {
        return clientLvrRepository.save(clientLvr);
    }
    @Override
    public ClientFct saveClientFct(ClientFct clientFct){
        return clientFctRepository.save(clientFct);
    }
    @Override
    public ClientFct getClientFctById(int id){
        return clientFctRepository.findClientFctById(id);
    }
    @Override
    public List<ClientFct> getClientFctList(){
        return clientFctRepository.findAll();
    }
    @Override
    public Page<ClientFct> getClientFctPages(int page, int size){
        return clientFctRepository.findAll(PageRequest.of(page,size));
    }
}

