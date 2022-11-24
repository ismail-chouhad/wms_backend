package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.Emplacement;
import com.chouhad.wms_backend.Entities.Depot;
import com.chouhad.wms_backend.Services.EmplacementService;
import com.chouhad.wms_backend.Services.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class EmplacementRestAPI {
    private EmplacementService emplacementService;
    private DepotService depotService;

    @GetMapping("/emplacements")
    public List<Emplacement> emplacementList() {
        return emplacementService.getEmplacementList();
    }
    @GetMapping("/emplacements/{id}")
    public Emplacement getEmplacement(@PathVariable int id){
        return emplacementService.getEmplacementById(id);
    }

    @GetMapping("/emplacements/{page}/{size}")
    public List<List<String>> getEmplacement(@PathVariable int page, @PathVariable int size){
        Page<Emplacement> emplacementPage = emplacementService.getEmplacementsPages(page,size);
        List<List<String>> list= new ArrayList<>();
        emplacementPage.forEach(emplacement-> {
            list.add(emplacement.getInfo());
        });
        return list;
    }

    @GetMapping("/emplacements/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return emplacementService.getEmplacementsPages(page,size).getTotalPages();
    }
    @GetMapping("/emplacements/getAllEmplacementsNames")
    public List<List<String>> getEmplacementsToStock() {
        List<Emplacement> emplacementList = emplacementService.getEmplacementList();
        List<List<String>> list = new ArrayList<>();
        for (Emplacement emplacement:emplacementList) {
            if(emplacement.getPallete() == null) {
                list.add(emplacement.getInfo());
            }
        }
        return list;
    }
    @PostMapping("/emplacements/save/{allee}/{nvHorizontal}/{nvVertical}/{depotId}")
    public String addEmplacement(@PathVariable String allee,
                                 @PathVariable String nvHorizontal,
                                 @PathVariable String nvVertical,
                                 @PathVariable int depotId){
        Depot depot = depotService.getDepotById(depotId);
        Emplacement emplacement = emplacementService.getEmplacementByAttributes(allee,nvHorizontal,nvVertical,depot);
        if(emplacement == null){
            Emplacement emplacement1 = new Emplacement();
            emplacement1.setAllee(allee);
            emplacement1.setNvHorizontal(nvHorizontal);
            emplacement1.setNvVertical(nvVertical);
            emplacement1.setDepot(depot);
            emplacementService.saveEmplacement(emplacement1);
            return "Emplacement ajouté";
        }
        return "Emplacement existe déjà";
    }
    @PutMapping("/emplacements/edit/{id}/{allee}/{nvHorizontal}/{nvVertical}/{depotId}")
    public String updateEmplacement(@PathVariable int id,@PathVariable String allee,
                                    @PathVariable String nvHorizontal,
                                    @PathVariable String nvVertical,
                                    @PathVariable int depotId){
        Depot depot = depotService.getDepotById(depotId);
        Emplacement emplacement = emplacementService.getEmplacementById(id);
        Emplacement emplacementByNameAndAddress = emplacementService.
                getEmplacementByAttributes(allee,nvHorizontal,nvVertical,depot);
        if(emplacement != null && emplacementByNameAndAddress == null){
            emplacement.setAllee(allee);
            emplacement.setNvHorizontal(nvHorizontal);
            emplacement.setNvVertical(nvVertical);
            emplacement.setDepot(depot);
            emplacementService.saveEmplacement(emplacement);
            return "Emplacement modifié";
        }
        return "Emplacement non modifié";
    }
    @DeleteMapping("/emplacements/delete/{id}")
    public void deleteEmplacement(@PathVariable int id){
        emplacementService.deleteEmplacement(id);
    }


}
