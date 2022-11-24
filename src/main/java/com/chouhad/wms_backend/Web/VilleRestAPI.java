package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Ville;
import com.chouhad.wms_backend.Services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class VilleRestAPI {
    private VilleService villeService;

    @GetMapping("/villes")
    public List<Ville> villeList() {
        return villeService.getVilleList();
    }
    @GetMapping("/villes/{id}")
    public Ville getVille(@PathVariable int id){
        return villeService.getVilleById(id);
    }

    @GetMapping("/villes/getId/{name}")
    public int getVilleIdByName(@PathVariable String name){
        Ville villeByName = villeService.getVilleByName(name);
        if(villeByName != null) {
            return villeByName.getId();
        }
        return -1;
    }

    @GetMapping("/villes/{page}/{size}")
    public List<String> getVille(@PathVariable int page, @PathVariable int size){
        Page<Ville> villePage = villeService.getVillesPages(page,size);
        List<String> villesName= new ArrayList<>();
        villePage.forEach(ville-> villesName.add(ville.getName()));
        return villesName;
    }
    @GetMapping("/villes/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return villeService.getVillesPages(page,size).getTotalPages();
    }
    @GetMapping("/villes/getAllVillesNames")
    public List<String> getListVillesNames(){
        List<Ville> villeList = villeService.getVilleList();
        List<String> listNames = new ArrayList<>();
        villeList.forEach(ville ->  {listNames.add(ville.getName());});
        return listNames;
    }
    @PostMapping("/villes/save/{name}")
    public String addVille(@PathVariable String name){
        Ville ville = villeService.getVilleByName(name);
        if(ville == null){
            Ville ville1 = new Ville();
            ville1.setName(name);
            villeService.saveVille(ville1);
            return "Ville ajoutée";
        }
        return "Ville existe déjà";
    }

    @PutMapping("/villes/edit/{id}/{name}")
    public String updateVille(@PathVariable int id, @PathVariable String name){
        Ville ville = villeService.getVilleById(id);
        Ville villeByName = villeService.getVilleByName(name);
        if(ville != null && villeByName == null){
            ville.setName(name);
            villeService.saveVille(ville);
            return "Ville modifiée";
        }
        return "Ville non modifiée";
    }
    @DeleteMapping("/villes/delete/{id}")
    public void deleteVille(@PathVariable int id){
        villeService.deleteVille(id);
    }


}
