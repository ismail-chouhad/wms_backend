package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Emplacement;
import com.chouhad.wms_backend.Repositories.EmplacementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class EmplacementServiceImpl implements EmplacementService {
    EmplacementRepository emplacementRepository;
    @Override
    public Emplacement saveEmplacement(Emplacement emplacement) {
        return emplacementRepository.save(emplacement);
    }
    @Override
    public Emplacement getEmplacementByAttributes(String allee, String nvHorizontal, String nvVertical, Depot depot){
        return emplacementRepository.findEmplacementByAlleeAndNvHorizontalAndNvVerticalAndDepot(
                allee,
                nvHorizontal,
                nvVertical,
                depot
        );
    }
    @Override
    public List<Emplacement> getEmplacementList(){
        return emplacementRepository.findAll();
    }
    @Override
    public Page<Emplacement> getEmplacementsPages(int page, int size){
        return emplacementRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Emplacement getEmplacementById(int id){
        return emplacementRepository.findEmplacementById(id);
    }
    @Override
    public void deleteEmplacement(int id){
        emplacementRepository.deleteEmplacementById(id);
    }
}

