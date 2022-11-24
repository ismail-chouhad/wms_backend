package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Ville;
import com.chouhad.wms_backend.Repositories.VilleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {
    VilleRepository villeRepository;
    @Override
    public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
    }
    @Override
    public String getVilleNameById(int id){
        return villeRepository.findVilleNameById(id).getName();
    }
    @Override
    public List<Ville> getVilleList(){
        return villeRepository.findAll();
    }
    @Override
    public Page<Ville> getVillesPages(int page, int size){
        return villeRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Ville getVilleByName(String name){
        return villeRepository.findVilleByName(name);
    }
    @Override
    public Ville getVilleById(int id){
        return villeRepository.findVilleById(id);
    }
    @Override
    public void deleteVille(int id){
        villeRepository.deleteVilleById(id);
    }
}

