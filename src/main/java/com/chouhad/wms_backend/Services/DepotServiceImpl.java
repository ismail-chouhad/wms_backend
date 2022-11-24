package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Repositories.DepotRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class DepotServiceImpl implements DepotService {
    DepotRepository depotRepository;
    @Override
    public Depot saveDepot(Depot depot) {
        return depotRepository.save(depot);
    }
    @Override
    public String getDepotNameById(int id){
        return depotRepository.findDepotNameById(id).getName();
    }
    @Override
    public List<Depot> getDepotList(){
        return depotRepository.findAll();
    }
    @Override
    public Page<Depot> getDepotsPages(int page, int size){
        return depotRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Depot getDepotByNameAndAddress(String name,String address){
        return depotRepository.findDepotByNameAndAddress(name,address);
    }
    @Override
    public Depot getDepotByName(String name){
        return depotRepository.findDepotByName(name);
    }
    @Override
    public Depot getDepotById(int id){
        return depotRepository.findDepotById(id);
    }
    @Override
    public void deleteDepot(int id){
        depotRepository.deleteDepotById(id);
    }
}

