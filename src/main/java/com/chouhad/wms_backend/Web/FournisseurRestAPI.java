package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Fournisseur;
import com.chouhad.wms_backend.Entities.FournisseurCategorie;
import com.chouhad.wms_backend.Entities.Pallete;
import com.chouhad.wms_backend.Services.FournisseurCategorieService;
import com.chouhad.wms_backend.Services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class FournisseurRestAPI {
    private FournisseurService fournisseurService;
    private FournisseurCategorieService fournisseurCategorieService;

    @PostMapping("/fournisseurs/save/{name}/{email}/{num}/{address}/{id}")
    public String postFournisseur(@PathVariable String name,@PathVariable String email,
                            @PathVariable String num,@PathVariable String address,@PathVariable int id){
        FournisseurCategorie fournisseurCategorieById = fournisseurCategorieService.getFournisseurCategorieById(id);
        Fournisseur fournisseur = fournisseurService.getFournisseurByAttributes(name,email,num,address);
        if(fournisseur == null)
        {
            Fournisseur fournisseur1 = new Fournisseur();
            fournisseur1.setName(name);
            fournisseur1.setEmail(email);
            fournisseur1.setNum(num);
            fournisseur1.setAddress(address);
            fournisseur1.setFournisseurCategorie(fournisseurCategorieById);
            fournisseurService.saveFournisseur(fournisseur1);
            return "Fournisseur ajoute";
        }
        return "Fournisseur existe deja";
    }
    @GetMapping("/fournisseurs/{page}/{size}")
    public List<List<String>> getPageFournisseurs(@PathVariable int page, @PathVariable int size){
        Page<Fournisseur> fournisseurPage = fournisseurService.getFournisseurPages(page,size);
        List<List<String>> list = new ArrayList<>();
        fournisseurPage.forEach(fournisseur -> { list.add(fournisseur.getInfo()); });
        return list;
    }
    @GetMapping("/fournisseurs")
    public List<Fournisseur> fournisseurList() {
        return fournisseurService.getFournisseurList();
    }
    @GetMapping("/fournisseurs/{id}")
    public Fournisseur getFournisseur(@PathVariable int id){
        return fournisseurService.getFournisseurById(id);
    }
    @GetMapping("/fournisseurs/getId/{name}")
    public int getFournisseurByName(@PathVariable String name){
        Fournisseur fournisseurByName = fournisseurService.getFournisseurByName(name);
        if(fournisseurByName != null) {
            return fournisseurByName.getId();
        }
        return -1;
    }
    @GetMapping("/fournisseurs/names/{page}/{size}")
    public List<String> getFournisseursNames(@PathVariable int page, @PathVariable int size){
        Page<Fournisseur> fournisseurPage = fournisseurService.getFournisseurPages(page,size);
        List<String> fournisseursNames= new ArrayList<>();
        fournisseurPage.forEach(cCP-> fournisseursNames.add(cCP.getName()));
        return fournisseursNames;
    }
    @GetMapping("/fournisseurs/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return fournisseurService.getFournisseurPages(page,size).getTotalPages();
    }
    @GetMapping("/fournisseurs/getAllFournisseursNames")
    public List<String> getAllPalletesNames(){
        List<Fournisseur> fournisseurList = fournisseurService.getFournisseurList();
        List<String> list = new ArrayList<>();
        fournisseurList.forEach(fournisseur -> {list.add(fournisseur.getName());});
        return list;
    }
    @PutMapping("/fournisseurs/edit/{id}/{name}/{email}/{num}/{address}/{CategorieId}")
    public String updateFournisseur(@PathVariable int id, @PathVariable String name,
                               @PathVariable String email, @PathVariable String num,
                               @PathVariable String address, @PathVariable int CategorieId){
        FournisseurCategorie fournisseurCategorie = fournisseurCategorieService.getFournisseurCategorieById(CategorieId);
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        Fournisseur fournisseurByAttributes = fournisseurService.getFournisseurByAttributes(name,email,num,address);
        if(fournisseur != null && fournisseurByAttributes == null){
            fournisseur.setName(name);
            fournisseur.setEmail(email);
            fournisseur.setNum(num);
            fournisseur.setAddress(address);
            fournisseurCategorie.setName(fournisseurCategorieService.getFournisseurCategorieNameById(CategorieId));
            fournisseur.setFournisseurCategorie(fournisseurCategorie);
            fournisseurService.saveFournisseur(fournisseur);
            return "Fournisseur modifié";
        }
        return "Fournisseur non modifié";
    }

    @GetMapping("/fournisseurs/stockGlobal/{page}/{size}")
    public List<List<String>> getPageFournisseursStockGlobal(@PathVariable int page, @PathVariable int size){
        Page<Fournisseur> fournisseurPage = fournisseurService.getFournisseurPages(page,size);
        List<List<String>> list = new ArrayList<>();

        fournisseurPage.forEach(fournisseur -> {list.add(fournisseur.getQuantityInfo());});
        return list;
    }

    @DeleteMapping("/fournisseurs/delete/{id}")
    public void deleteFournisseur(@PathVariable int id){
        fournisseurService.deleteFournisseur(id);
    }
}
