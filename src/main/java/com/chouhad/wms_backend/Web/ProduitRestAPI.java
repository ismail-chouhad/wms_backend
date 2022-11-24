package com.chouhad.wms_backend.Web;
import com.chouhad.wms_backend.Entities.*;
import com.chouhad.wms_backend.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProduitRestAPI {

    private ProduitService produitService;
    private MarqueService marqueService;
    private FamilleService familleService;
    private TVAService tvaService;
    private DateExpService dateExpService;
    private DatePrdService datePrdService;


    @PostMapping("/produits/save/{barCode}/{name}/{priceUnit}/{priceTTC}/" +
            "{marqueName}/{familleName}/{tvaTaux}/{datePrd}/{dateExp}")
    public String postProduit(@PathVariable long barCode , @PathVariable String name ,
                              @PathVariable Float priceUnit , @PathVariable Float priceTTC ,
                              @PathVariable String marqueName , @PathVariable String familleName ,
                              @PathVariable Float tvaTaux , @PathVariable String datePrd ,
                              @PathVariable String dateExp) {
        Marque marque = marqueService.getMarqueByName(marqueName);
        Famille famille = familleService.getFamilleByName(familleName);
        TVA tva = tvaService.getTVAByTaux(tvaTaux);
        DatePrd datePrd1 = datePrdService.getDatePrdByDate(datePrd);
        DateExp dateExp1 = dateExpService.getDateExpByDate(dateExp);
        List<Produit> produitsList = produitService.getProduitsByName(name);
        if(produitsList.isEmpty()) {
            Produit produit = new Produit();
            produit.setBarCode(barCode);
            produit.setName(name);
            produit.setPriceUnit(priceUnit);
            produit.setPriceTTC(priceTTC);
            produit.setPriceHT( (priceTTC*100)/(100+tvaTaux) );
            produit.setMarque(marque);
            produit.setFamille(famille);
            produit.setTva(tva);
            produit.setDatePrd(datePrd1);
            produit.setDateExp(dateExp1);
            produitService.saveProduit(produit);
            return "Produit ajouté";
        }
        return "Produit existe déjà";
    }
    @PutMapping("/produits/edit/{id}/{barCode}/{name}/{priceUnit}/{priceTTC}/" +
            "{marqueName}/{familleName}/{tvaTaux}/{datePrd}/{dateExp}")
    public String putProduit(@PathVariable int id, @PathVariable long barCode , @PathVariable String name ,
                              @PathVariable Float priceUnit , @PathVariable Float priceTTC ,
                              @PathVariable String marqueName , @PathVariable String familleName ,
                              @PathVariable Float tvaTaux , @PathVariable String datePrd ,
                              @PathVariable String dateExp) {
        Produit produit = produitService.getProduitById(id);
        Marque marque = marqueService.getMarqueByName(marqueName);
        Famille famille = familleService.getFamilleByName(familleName);
        TVA tva = tvaService.getTVAByTaux(tvaTaux);
        DatePrd datePrd1 = datePrdService.getDatePrdByDate(datePrd);
        DateExp dateExp1 = dateExpService.getDateExpByDate(dateExp);
        if(produit!=null) {
            produit.setBarCode(barCode);
            produit.setName(name);
            produit.setPriceUnit(priceUnit);
            produit.setPriceTTC(priceTTC);
            produit.setPriceHT( (priceTTC*100)/(100+tvaTaux) );
            produit.setMarque(marque);
            produit.setFamille(famille);
            produit.setTva(tva);
            produit.setDatePrd(datePrd1);
            produit.setDateExp(dateExp1);
            produitService.saveProduit(produit);
            return "Produit modifié";
        }
        return "Produit non modifié";
    }
    @GetMapping("/produits/{page}/{size}")
    public List<List<String>> getPageProduits(@PathVariable int page, @PathVariable int size){
        Page<Produit> produitPage = produitService.getProduits(page,size);
        List<List<String>> list = new ArrayList<>();
        produitPage.forEach(produit -> { list.add(produit.getInfo()); });
        return list;
    }
    @GetMapping("/produits/getId/{name}")
    public int getProduitIdByName(@PathVariable String name){
         Produit produit = produitService.getProduitByName(name);
         if(produit!=null){
             return produit.getId();
         }
         return -1;
    }
    @GetMapping("/produits/getBarCodes")
    public List<String> getBarCodes(){
        List<Produit> produitsList = produitService.getListProduits();
        List<String> list = new ArrayList<>();
        for (Produit produit:produitsList) {
            list.add(Long.toString(produit.getBarCode()));
        }
        return list;
    }
    @GetMapping("/produits/totalPages/{page}/{size}")
    public int getTotalPagesProduits(@PathVariable int page, @PathVariable int size){
        return produitService.getProduits(page,size).getTotalPages();
    }
    @GetMapping("/produits/getByFamille/{familleId}")
    public List<String> getByFamille(@PathVariable int familleId){
        Famille famille = familleService.getFamilleById(familleId);
        List<Produit> produitsList = produitService.getProduitsByFamille(famille);
        List<String> list = new ArrayList<>();
        if(produitsList != null) {
            for (Produit produit:produitsList) {
                list.add(Long.toString(produit.getBarCode()));
            }
        }
        else {
            list.add("Aucun produit");
        }
        return list;
    }
    @GetMapping("/produits/getAllProduitsNames")
    public List<String> getAllProduitsNames(){
        List<Produit> produitList = produitService.getListProduits();
        List<String> list = new ArrayList<>();
        produitList.forEach(produit -> {list.add(produit.getName());});
        return list;
    }
    @GetMapping("/produits/stockGlobal/{page}/{size}")
    public List<List<String>> getPageProduitsStockGlobal(@PathVariable int page, @PathVariable int size){
        Page<Produit> produitPage = produitService.getProduits(page,size);
        List<List<String>> list = new ArrayList<>();

        produitPage.forEach(produit -> {list.add(produit.getQuantityInfo());});
        return list;
    }
    @DeleteMapping("/produits/delete/{id}")
    public void deleteProduitByBarCode(@PathVariable int id){
        produitService.deleteProduitById(id);
    }
}
