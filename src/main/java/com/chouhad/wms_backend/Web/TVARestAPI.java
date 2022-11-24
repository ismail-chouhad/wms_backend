package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.TVA;
import com.chouhad.wms_backend.Services.TVAService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class TVARestAPI {
    private TVAService tvaService;

    @GetMapping("/tvas")
    public List<TVA> tvaList() {
        return tvaService.getTVAList();
    }
    @GetMapping("/tvas/{id}")
    public TVA getTVA(@PathVariable int id){
        return tvaService.getTVAById(id);
    }

    @GetMapping("/tvas/getId/{taux}")
    public int getTVAIdByTaux(@PathVariable float taux){
        TVA tvaByTaux = tvaService.getTVAByTaux(taux);
        if(tvaByTaux != null) {
            return tvaByTaux.getId();
        }
        return -1;
    }
    @GetMapping("/tvas/getTva/{taux}")
    public TVA getTVAByTaux(@PathVariable float taux){
        return tvaService.getTVAByTaux(taux);
    }
    @GetMapping("/tvas/{page}/{size}")
    public List<Float> getTVA(@PathVariable int page, @PathVariable int size){
        Page<TVA> tvaPage = tvaService.getTVAsPages(page,size);
        List<Float> tvasTaux= new ArrayList<>();
        tvaPage.forEach(tva-> tvasTaux.add(tva.getTaux()));
        return tvasTaux;
    }
    @GetMapping("/tvas/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return tvaService.getTVAsPages(page,size).getTotalPages();
    }
    @GetMapping("/tvas/getAllTVAsTauxs")
    public List<Float> getListTVAsTauxs(){
        List<TVA> tvaList = tvaService.getTVAList();
        List<Float> listTauxs = new ArrayList<>();
        tvaList.forEach(tva ->  {listTauxs.add(tva.getTaux());});
        return listTauxs;
    }
    @PostMapping("/tvas/save/{taux}")
    public String addTVA(@PathVariable float taux){
        TVA tva = tvaService.getTVAByTaux(taux);
        if(tva == null){
            TVA tva1 = new TVA();
            tva1.setTaux(taux);
            tvaService.saveTVA(tva1);
            return "TVA ajoutée";
        }
        return "TVA existe déjà";
    }

    @PutMapping("/tvas/edit/{id}/{taux}")
    public String updateTVA(@PathVariable int id, @PathVariable Float taux){
        TVA tva = tvaService.getTVAById(id);
        TVA tvaByTaux = tvaService.getTVAByTaux(taux);
        if(tva != null && tvaByTaux == null){
            tva.setTaux(taux);
            tvaService.saveTVA(tva);
            return "TVA modifiée";
        }
        return "TVA non modifiée";
    }

    @DeleteMapping("/tvas/delete/{id}")
    public void deleteTVA(@PathVariable int id){
        tvaService.deleteTVA(id);
    }

}
