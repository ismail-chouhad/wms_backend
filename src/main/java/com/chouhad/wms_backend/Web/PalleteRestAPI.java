package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Pallete;
import com.chouhad.wms_backend.Entities.Produit;
import com.chouhad.wms_backend.Services.ProduitService;
import com.chouhad.wms_backend.Services.PalleteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class PalleteRestAPI {
    private PalleteService palleteService;
    private ProduitService produitService;

    @PostMapping("/palletes/save/{name}/{numSerie}/{quantity}/{id}")
    public String postPallete(@PathVariable String name,@PathVariable String numSerie,
                            @PathVariable long quantity, @PathVariable int id){
        Produit produitById = produitService.getProduitById(id);
        Pallete pallete = palleteService.getPalleteByAttributes(name,numSerie,quantity);
        if(pallete == null)
        {
            Pallete pallete1 = new Pallete();
            pallete1.setName(name);
            pallete1.setNumSerie(numSerie);
            pallete1.setQuantity(quantity);;
            pallete1.setProduit(produitById);
            palleteService.savePallete(pallete1);
            return "Pallete ajoutée";
        }
        return "Pallete existe deja";
    }
    @GetMapping("/palletes/{page}/{size}")
    public List<List<String>> getPagePalletes(@PathVariable int page, @PathVariable int size){
        Page<Pallete> palletePage = palleteService.getPalletePages(page,size);
        List<List<String>> list = new ArrayList<>();
        palletePage.forEach(pallete -> { list.add(pallete.getInfo()); });
        return list;
    }
    @GetMapping("/palletes")
    public List<Pallete> palleteList() {
        return palleteService.getPalleteList();
    }
    @GetMapping("/palletes/{id}")
    public Pallete getPallete(@PathVariable int id){
        return palleteService.getPalleteById(id);
    }
    @GetMapping("/palletes/getId/{name}")
    public int getPalleteByName(@PathVariable String name){
        Pallete palleteByName = palleteService.getPalleteByName(name);
        if(palleteByName != null) {
            return palleteByName.getId();
        }
        return -1;
    }
    @GetMapping("/palletes/names/{page}/{size}")
    public List<String> getPalletesNames(@PathVariable int page, @PathVariable int size){
        Page<Pallete> palletePage = palleteService.getPalletePages(page,size);
        List<String> palletesNames= new ArrayList<>();
        palletePage.forEach(cCP-> palletesNames.add(cCP.getName()));
        return palletesNames;
    }
    @GetMapping("/palletes/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return palleteService.getPalletePages(page,size).getTotalPages();
    }
    @GetMapping("/palletes/getAllPalletesNames")
    public List<String> getAllPalletesNames(){
        List<Pallete> palleteList = palleteService.getPalleteList();
        List<String> list = new ArrayList<>();
        palleteList.forEach(pallete -> {list.add(pallete.getName());});
        return list;
    }
    @PutMapping("/palletes/edit/{id}/{name}/{numSerie}/{quantity}/{produitId}")
    public String updatePallete(@PathVariable int id, @PathVariable String name,
                               @PathVariable String numSerie, @PathVariable long quantity,
                               @PathVariable int produitId){
        Produit produit = produitService.getProduitById(produitId);
        Pallete pallete = palleteService.getPalleteById(id);
        if(pallete != null){
            pallete.setName(name);
            pallete.setNumSerie(numSerie);
            pallete.setQuantity(quantity);
            pallete.setProduit(produit);
            palleteService.savePallete(pallete);
            return "Pallete modifiée";
        }
        return "Pallete non modifiée";
    }
    @DeleteMapping("/palletes/delete/{id}")
    public void deletePallete(@PathVariable int id){
        palleteService.deletePallete(id);
    }
}
