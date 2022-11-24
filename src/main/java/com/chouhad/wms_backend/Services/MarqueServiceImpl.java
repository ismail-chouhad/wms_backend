package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Marque;
import com.chouhad.wms_backend.Repositories.MarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class MarqueServiceImpl implements MarqueService {
    MarqueRepository marqueRepository;
    @Override
    public Marque saveMarque(Marque marque) {
        return marqueRepository.save(marque);
    }
    @Override
    public String getMarqueNameById(int id){
        return marqueRepository.findMarqueNameById(id).getName();
    }
    @Override
    public List<Marque> getMarqueList(){
        return marqueRepository.findAll();
    }
    @Override
    public Page<Marque> getMarquesPages(int page, int size){
        return marqueRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Marque getMarqueByName(String name){
        return marqueRepository.findMarqueByName(name);
    }
    @Override
    public Marque getMarqueById(int id){
        return marqueRepository.findMarqueById(id);
    }
    @Override
    public void deleteMarque(int id){
        marqueRepository.deleteMarqueById(id);
    }
}

