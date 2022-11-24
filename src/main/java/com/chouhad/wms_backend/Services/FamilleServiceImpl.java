package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Famille;
import com.chouhad.wms_backend.Repositories.FamilleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class FamilleServiceImpl implements FamilleService {
    FamilleRepository familleRepository;
    @Override
    public Famille saveFamille(Famille famille) {
        return familleRepository.save(famille);
    }
    @Override
    public String getFamilleNameById(int id){
        return familleRepository.findFamilleNameById(id).getName();
    }
    @Override
    public List<Famille> getFamilleList(){
        return familleRepository.findAll();
    }
    @Override
    public Page<Famille> getFamillesPages(int page, int size){
        return familleRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Famille getFamilleByName(String name){
        return familleRepository.findFamilleByName(name);
    }
    @Override
    public Famille getFamilleById(int id){
        return familleRepository.findFamilleById(id);
    }
    @Override
    public void deleteFamille(int id){
        familleRepository.deleteFamilleById(id);
    }
}

