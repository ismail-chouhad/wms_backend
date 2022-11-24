package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Fournisseur;
import com.chouhad.wms_backend.Repositories.FournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {
    FournisseurRepository fournisseurRepository;
    @Override
    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
    @Override
    public List<Fournisseur> getFournisseurList(){
        return fournisseurRepository.findAll();
    }
    @Override
    public Page<Fournisseur> getFournisseurPages(int page, int size){
        return fournisseurRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public Fournisseur getFournisseurByAttributes(String name, String email, String num, String address){
        return fournisseurRepository.findFournisseurByNameAndEmailAndNumAndAddress(name,email,num,address);
    }
    @Override
    public Fournisseur getFournisseurByName(String name){
        return fournisseurRepository.findFournisseurByName(name);
    }
    @Override
    public Fournisseur getFournisseurById(int id){
        return fournisseurRepository.findFournisseurById(id);
    }
    @Override
    public void deleteFournisseur(int id){
        fournisseurRepository.deleteFournisseurById(id);
    }
}

