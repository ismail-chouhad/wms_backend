package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.Famille;
import com.chouhad.wms_backend.Entities.Produit;
import com.chouhad.wms_backend.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService{

    private ProduitRepository produitRepository;

    @Override
    public Produit getProduitById(int id){
        return produitRepository.findProduitById(id);
    }
    @Override
    public Produit getProduitByName(String name){
        return produitRepository.findProduitByName(name);
    }

    @Override
    public String getProduitNameById(int id) {
        return produitRepository.findProduitNameById(id);
    }
    @Override
    public List<Produit> getProduitsByFamille(Famille famille){
        return produitRepository.findProduitsByFamille(famille);
    }
    @Override
    public List<Produit> getProduitsByName(String name) {
        return produitRepository.findProduitsByName(name);
    }

    @Override
    public Page<Produit> getProduits(int page, int size) {
        return produitRepository.findAll(PageRequest.of(page , size));
    }

    @Override
    public List<Produit> getListProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    @Override
    public void deleteProduitById(int id){
        produitRepository.deleteProduitById(id);
    }
}