package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.*;
import com.chouhad.wms_backend.Repositories.FournisseurCmdRepository;
import com.chouhad.wms_backend.Repositories.FournisseurFctRepository;
import com.chouhad.wms_backend.Repositories.FournisseurRspRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class FournisseurMvtServiceImpl implements FournisseurMvtService {
    FournisseurCmdRepository fournisseurCmdRepository;
    FournisseurRspRepository fournisseurRspRepository;
    FournisseurFctRepository fournisseurFctRepository;
    @Override
    public FournisseurCmd saveFournisseurCmd(FournisseurCmd fournisseurCmd) {
        return fournisseurCmdRepository.save(fournisseurCmd);
    }
    @Override
    public FournisseurRsp saveFournisseurRsp(FournisseurRsp fournisseurRsp) {
        return fournisseurRspRepository.save(fournisseurRsp);
    }
    @Override
    public FournisseurFct saveFournisseurFct(FournisseurFct fournisseurFct){
        return fournisseurFctRepository.save(fournisseurFct);
    }
    @Override
    public FournisseurFct getFournisseurFctById(int id){
        return fournisseurFctRepository.findFournisseurFctById(id);
    }
    @Override
    public List<FournisseurFct> getFournisseurFctList(){
        return fournisseurFctRepository.findAll();
    }
    @Override
    public Page<FournisseurFct> getFournisseurFctPages(int page, int size){
        return fournisseurFctRepository.findAll(PageRequest.of(page,size));
    }
}

