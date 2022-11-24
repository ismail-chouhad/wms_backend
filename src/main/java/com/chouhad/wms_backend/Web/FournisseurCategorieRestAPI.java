package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.FournisseurCategorie;
import com.chouhad.wms_backend.Services.FournisseurCategorieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class FournisseurCategorieRestAPI {
    FournisseurCategorieService fournisseurCategorieService;

    @GetMapping("/fournisseurCategories")
    public List<FournisseurCategorie> fournisseurCategorieList() {
        return fournisseurCategorieService.getFournisseurCategorieList();
    }
    @GetMapping("/fournisseurCategories/{id}")
    public FournisseurCategorie getFournisseurCategorie(@PathVariable int id){
        return fournisseurCategorieService.getFournisseurCategorieById(id);
    }
    @GetMapping("/fournisseurCategories/getId/{name}")
    public int getFournisseurCategorieByName(@PathVariable String name){
        FournisseurCategorie fournisseurCategorieByName = fournisseurCategorieService.getFournisseurCategorieByName(name);
        if(fournisseurCategorieByName != null) {
            return fournisseurCategorieByName.getId();
        }
        return -1;
    }
    @GetMapping("/fournisseurCategories/{page}/{size}")
    public List<String> getFournisseurCategorie(@PathVariable int page, @PathVariable int size){
        Page<FournisseurCategorie> fournisseurCategoriePage = fournisseurCategorieService.getCategoriesPages(page,size);
        List<String> fournisseurCategoriesName= new ArrayList<>();
        fournisseurCategoriePage.forEach(cCP-> fournisseurCategoriesName.add(cCP.getName()));
        return fournisseurCategoriesName;
    }
    @GetMapping("/fournisseurCategories/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return fournisseurCategorieService.getCategoriesPages(page,size).getTotalPages();
    }
    @GetMapping("/fournisseurCategories/getAllCategoriesNames")
    public List<String> getListCategoriesNames(){
        List<FournisseurCategorie> fournisseurCategorieList = fournisseurCategorieService.getFournisseurCategorieList();
        List<String> listNames = new ArrayList<>();
        fournisseurCategorieList.forEach(categoriefournisseur ->  {listNames.add(categoriefournisseur.getName());});
        return listNames;
    }

    @PostMapping("/fournisseurCategories/save/{name}")
    public String addClientCategorie(@PathVariable String name){
        FournisseurCategorie fournisseurCategorie = fournisseurCategorieService.getFournisseurCategorieByName(name);
        if(fournisseurCategorie == null){
            FournisseurCategorie fournisseurCategorie1 = new FournisseurCategorie();
            fournisseurCategorie1.setName(name);
            fournisseurCategorieService.saveFournisseurCategorie(fournisseurCategorie1);
            return "Catégorie ajoutée";
        }
        return "Catégorie existe déjà";
    }

    @PutMapping("/fournisseurCategories/edit/{id}/{name}")
    public String updateFournisseurCategorie(@PathVariable int id, @PathVariable String name){
        FournisseurCategorie fournisseurCategorie = fournisseurCategorieService.getFournisseurCategorieById(id);
        FournisseurCategorie fournisseurCategorieByName = fournisseurCategorieService.getFournisseurCategorieByName(name);
        if(fournisseurCategorie != null && fournisseurCategorieByName == null){
            fournisseurCategorie.setName(name);
            fournisseurCategorieService.saveFournisseurCategorie(fournisseurCategorie);
            return "Catégorie modifiée";
        }
        return "Catégorie non modifiée";
    }
    @DeleteMapping("/fournisseurCategories/delete/{id}")
    public void deleteFournisseurCategorie(@PathVariable int id){
        fournisseurCategorieService.deleteFournisseurCategorie(id);
    }


}
