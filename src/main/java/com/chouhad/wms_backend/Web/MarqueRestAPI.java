package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Marque;
import com.chouhad.wms_backend.Services.MarqueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class MarqueRestAPI {
    private MarqueService marqueService;

    @GetMapping("/marques")
    public List<Marque> marqueList() {
        return marqueService.getMarqueList();
    }
    @GetMapping("/marques/{id}")
    public Marque getMarque(@PathVariable int id){
        return marqueService.getMarqueById(id);
    }

    @GetMapping("/marques/getId/{name}")
    public int getMarqueByName(@PathVariable String name){
        Marque marqueByName = marqueService.getMarqueByName(name);
        if(marqueByName != null) {
            return marqueByName.getId();
        }
        return -1;
    }

    @GetMapping("/marques/{page}/{size}")
    public List<String> getMarque(@PathVariable int page, @PathVariable int size){
        Page<Marque> marquePage = marqueService.getMarquesPages(page,size);
        List<String> marquesName= new ArrayList<>();
        marquePage.forEach(marque-> marquesName.add(marque.getName()));
        return marquesName;
    }
    @GetMapping("/marques/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return marqueService.getMarquesPages(page,size).getTotalPages();
    }
    @GetMapping("/marques/getAllMarquesNames")
    public List<String> getListMarquesNames(){
        List<Marque> marqueList = marqueService.getMarqueList();
        List<String> listNames = new ArrayList<>();
        marqueList.forEach(marque ->  {listNames.add(marque.getName());});
        return listNames;
    }
    @PostMapping("/marques/save/{name}")
    public String addMarque(@PathVariable String name){
        Marque marque = marqueService.getMarqueByName(name);
        if(marque == null){
            Marque marque1 = new Marque();
            marque1.setName(name);
            marqueService.saveMarque(marque1);
            return "Marque ajoutée";
        }
        return "Marque existe déjà";
    }

    @PutMapping("/marques/edit/{id}/{name}")
    public String updateMarque(@PathVariable int id, @PathVariable String name){
        Marque marque = marqueService.getMarqueById(id);
        Marque marqueByName = marqueService.getMarqueByName(name);
        if(marque != null && marqueByName == null){
            marque.setName(name);
            marqueService.saveMarque(marque);
            return "Marque modifiée";
        }
        return "Marque non modifiée";
    }
    @DeleteMapping("/marques/delete/{id}")
    public void deleteMarque(@PathVariable int id){
        marqueService.deleteMarque(id);
    }


}
