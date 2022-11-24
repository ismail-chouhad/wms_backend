package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Pallete;
import com.chouhad.wms_backend.Repositories.PalleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class PalleteServiceImpl implements PalleteService {
    PalleteRepository palleteRepository;
    @Override
    public Pallete savePallete(Pallete pallete) {
        return palleteRepository.save(pallete);
    }
    @Override
    public List<Pallete> getPalleteList(){
        return palleteRepository.findAll();
    }
    @Override
    public Page<Pallete> getPalletePages(int page, int size){
        return palleteRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Pallete getPalleteByAttributes(String name, String numSerie, long quantity){
        return palleteRepository.findPalleteByNameAndNumSerieAndQuantity(name,numSerie,quantity);
    }
    @Override
    public Pallete getPalleteByName(String name){
        return palleteRepository.findPalleteByName(name);
    }
    @Override
    public Pallete getPalleteById(int id){
        return palleteRepository.findPalleteById(id);
    }
    @Override
    public void deletePallete(int id){
        palleteRepository.deletePalleteById(id);
    }
}

