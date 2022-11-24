package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Entities.Ville;
import com.chouhad.wms_backend.Services.DepotService;
import com.chouhad.wms_backend.Services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class DepotRestAPI {
    private DepotService depotService;
    private VilleService villeService;

    @GetMapping("/depots")
    public List<Depot> depotList() {
        return depotService.getDepotList();
    }
    @GetMapping("/depots/{id}")
    public Depot getDepot(@PathVariable int id){
        return depotService.getDepotById(id);
    }

    @GetMapping("/depots/getId/{name}")
    public int getDepotIdByName(@PathVariable String name){
        Depot depotByName = depotService.getDepotByName(name);
        if(depotByName != null) {
            return depotByName.getId();
        }
        return -1;
    }

    @GetMapping("/depots/{page}/{size}")
    public List<List<String>> getDepot(@PathVariable int page, @PathVariable int size){
        Page<Depot> depotPage = depotService.getDepotsPages(page,size);
        List<List<String>> list= new ArrayList<>();
        depotPage.forEach(depot-> {
            list.add(depot.getInfo());
        });
        return list;
    }

    @GetMapping("/depots/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return depotService.getDepotsPages(page,size).getTotalPages();
    }
    @GetMapping("/depots/getAllDepotsNames")
    public List<String> getListDepotsNames(){
        List<Depot> depotList = depotService.getDepotList();
        List<String> listNames = new ArrayList<>();
        depotList.forEach(depot ->  {listNames.add(depot.getName());});
        return listNames;
    }
    @PostMapping("/depots/save/{name}/{address}/{villeId}")
    public String addDepot(@PathVariable String name,
                           @PathVariable String address,
                           @PathVariable int villeId){
        Depot depot = depotService.getDepotByNameAndAddress(name,address);
        Ville ville = villeService.getVilleById(villeId);
        if(depot == null){
            Depot depot1 = new Depot();
            depot1.setName(name);
            depot1.setAddress(address);
            depot1.setVille(ville);
            depotService.saveDepot(depot1);
            return "Depot ajouté";
        }
        return "Depot existe déjà";
    }

    @PutMapping("/depots/edit/{id}/{name}/{address}/{villeId}")
    public String updateDepot(@PathVariable int id, @PathVariable String name,
                              @PathVariable String address,
                              @PathVariable int villeId){
        Depot depot = depotService.getDepotById(id);
        Depot depotByNameAndAddress = depotService.getDepotByNameAndAddress(name,address);
        Ville ville = villeService.getVilleById(villeId);
        if(depot != null && depotByNameAndAddress == null){
            depot.setName(name);
            depot.setAddress(address);
            depot.setVille(ville);
            depotService.saveDepot(depot);
            return "Depot modifié";
        }
        return "Depot non modifié";
    }
    @GetMapping("/depots/stockGlobal/{page}/{size}")
    public List<List<String>> getPageDepotsStockGlobal(@PathVariable int page, @PathVariable int size){
        Page<Depot> depotPage = depotService.getDepotsPages(page,size);
        List<List<String>> list = new ArrayList<>();

        depotPage.forEach(depot -> {list.add(depot.getQuantityInfo());});
        return list;
    }
    @DeleteMapping("/depots/delete/{id}")
    public void deleteDepot(@PathVariable int id){
        depotService.deleteDepot(id);
    }


}
