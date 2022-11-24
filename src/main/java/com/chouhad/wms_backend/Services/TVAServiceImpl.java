package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.TVA;
import com.chouhad.wms_backend.Repositories.TVARepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class TVAServiceImpl implements TVAService {
    TVARepository tvaRepository;
    @Override
    public TVA saveTVA(TVA tva) {
        return tvaRepository.save(tva);
    }
    @Override
    public List<TVA> getTVAList(){
        return tvaRepository.findAll();
    }
    @Override
    public Page<TVA> getTVAsPages(int page, int size){
        return tvaRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public TVA getTVAByTaux(float taux){
        return tvaRepository.findTVAByTaux(taux);
    }
    @Override
    public TVA getTVAById(int id){
        return tvaRepository.findTVAById(id);
    }
    @Override
    public void deleteTVA(int id){
        tvaRepository.deleteTVAById(id);
    }
}

