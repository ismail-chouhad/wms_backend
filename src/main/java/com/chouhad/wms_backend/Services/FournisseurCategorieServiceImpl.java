package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.FournisseurCategorie;
import com.chouhad.wms_backend.Repositories.FournisseurCategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class FournisseurCategorieServiceImpl implements FournisseurCategorieService {
    FournisseurCategorieRepository fournisseurCategorieRepository;

    @Override
    public FournisseurCategorie saveFournisseurCategorie(FournisseurCategorie fournisseurCategorie) {
        return fournisseurCategorieRepository.save(fournisseurCategorie);
    }
    @Override
    public String getFournisseurCategorieNameById(int id){
        return fournisseurCategorieRepository.findFournisseurCategorieNameById(id).getName();
    }
    @Override
    public List<FournisseurCategorie> getFournisseurCategorieList(){
        return fournisseurCategorieRepository.findAll();
    }
    @Override
    public Page<FournisseurCategorie> getCategoriesPages(int page, int size){
        return fournisseurCategorieRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public FournisseurCategorie getFournisseurCategorieByName(String name){
        return fournisseurCategorieRepository.findFournisseurCategorieByName(name);
    }
    @Override
    public FournisseurCategorie getFournisseurCategorieById(int id){
        return fournisseurCategorieRepository.findFournisseurCategorieById(id);
    }
    @Override
    public void deleteFournisseurCategorie(int id){
        fournisseurCategorieRepository.deleteFournisseurCategorieById(id);
    }
}

