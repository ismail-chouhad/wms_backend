package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Famille;
import com.chouhad.wms_backend.Services.FamilleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class FamilleRestAPI {
    private FamilleService familleService;

    @GetMapping("/familles")
    public List<Famille> familleList() {
        return familleService.getFamilleList();
    }
    @GetMapping("/familles/{id}")
    public Famille getFamille(@PathVariable int id){
        return familleService.getFamilleById(id);
    }

    @GetMapping("/familles/getId/{name}")
    public int getFamilleIdByName(@PathVariable String name){
        Famille familleByName = familleService.getFamilleByName(name);
        if(familleByName != null) {
            return familleByName.getId();
        }
        return -1;
    }

    @GetMapping("/familles/{page}/{size}")
    public List<String> getFamille(@PathVariable int page, @PathVariable int size){
        Page<Famille> famillePage = familleService.getFamillesPages(page,size);
        List<String> famillesName= new ArrayList<>();
        famillePage.forEach(famille-> famillesName.add(famille.getName()));
        return famillesName;
    }
    @GetMapping("/familles/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return familleService.getFamillesPages(page,size).getTotalPages();
    }
    @GetMapping("/familles/getAllFamillesNames")
    public List<String> getListFamillesNames(){
        List<Famille> familleList = familleService.getFamilleList();
        List<String> listNames = new ArrayList<>();
        familleList.forEach(famille ->  {listNames.add(famille.getName());});
        return listNames;
    }
    @PostMapping("/familles/save/{name}")
    public String addFamille(@PathVariable String name){
        Famille famille = familleService.getFamilleByName(name);
        if(famille == null){
            Famille famille1 = new Famille();
            famille1.setName(name);
            familleService.saveFamille(famille1);
            return "Famille ajoutée";
        }
        return "Famille existe déjà";
    }

    @PutMapping("/familles/edit/{id}/{name}")
    public String updateFamille(@PathVariable int id, @PathVariable String name){
        Famille famille = familleService.getFamilleById(id);
        Famille familleByName = familleService.getFamilleByName(name);
        if(famille != null && familleByName == null){
            famille.setName(name);
            familleService.saveFamille(famille);
            return "Famille modifiée";
        }
        return "Famille non modifiée";
    }
    @GetMapping("/familles/stockGlobal/{page}/{size}")
    public List<List<String>> getPageFamillesStockGlobal(@PathVariable int page, @PathVariable int size){
        Page<Famille> famillePage = familleService.getFamillesPages(page,size);
        List<List<String>> list = new ArrayList<>();

        famillePage.forEach(famille -> {list.add(famille.getQuantityInfo());});
        return list;
    }
    @DeleteMapping("/familles/delete/{id}")
    public void deleteFamille(@PathVariable int id){
        familleService.deleteFamille(id);
    }


}
