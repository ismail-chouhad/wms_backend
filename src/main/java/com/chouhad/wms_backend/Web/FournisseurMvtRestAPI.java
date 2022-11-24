package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.*;
import com.chouhad.wms_backend.Services.EmplacementService;
import com.chouhad.wms_backend.Services.FournisseurService;
import com.chouhad.wms_backend.Services.FournisseurMvtService;
import com.chouhad.wms_backend.Services.PalleteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class FournisseurMvtRestAPI {
    private FournisseurMvtService fournisseurMvtService;
    private PalleteService palleteService;
    private FournisseurService fournisseurService;
    private EmplacementService emplacementService;

    @PostMapping("/fournisseurCmds/save/{fournisseurName}/{palleteName}")
    public String postCmdFour(@PathVariable String fournisseurName, @PathVariable String palleteName) {
        Pallete pallete = palleteService.getPalleteByName(palleteName);
        Fournisseur fournisseur = fournisseurService.getFournisseurByName(fournisseurName);

        if(pallete.getFournisseurCmd()!=null && pallete.getFournisseurCmd().getFournisseur().getName().equals(fournisseurName)) {
            return "Cette pallete est déjà enregistrée dans une commande";
        }

        FournisseurCmd fournisseurCmd = new FournisseurCmd();

        fournisseurCmd.setFournisseur(fournisseur);
        fournisseurCmd.setPallete(pallete);
        fournisseurMvtService.saveFournisseurCmd(fournisseurCmd);

        pallete.setFournisseurCmd(fournisseurCmd);
        palleteService.savePallete(pallete);

        return "Commande bien enregistrée";
    }
    @GetMapping("/listPalettesToCmd")
    public List<List<String>> getListPalettesToCmd() {
        List<Pallete> paletteList = palleteService.getPalleteList();
        List<List<String>> list = new ArrayList<>();

        for (Pallete palette:paletteList) {
            if(palette.getFournisseurCmd() == null) {
                list.add(palette.getInfo());
            }
        }
        return list;
    }

    @PostMapping("/fournisseurRsps/save/{palleteName}/{emplacementId}")
    public String postRspFour(@PathVariable String palleteName, @PathVariable int emplacementId) {
        Pallete pallete = palleteService.getPalleteByName(palleteName);
        Emplacement emplacement = emplacementService.getEmplacementById(emplacementId);
        Fournisseur fournisseur = pallete.getFournisseurCmd().getFournisseur();

        FournisseurRsp fournisseurRsp = new FournisseurRsp();
        FournisseurFct fournisseurFct = new FournisseurFct();

        fournisseurRsp.setFournisseur(fournisseur);
        fournisseurRsp.setPallete(pallete);
        fournisseurMvtService.saveFournisseurRsp(fournisseurRsp);

        fournisseurFct.setFournisseur(fournisseur);
        fournisseurFct.setPallete(pallete);
        fournisseurMvtService.saveFournisseurFct(fournisseurFct);

        pallete.setFournisseurRsp(fournisseurRsp);
        pallete.setFournisseurFct(fournisseurFct);
        pallete.setEmplacement(emplacement);
        palleteService.savePallete(pallete);

        emplacement.setPallete(pallete);
        emplacementService.saveEmplacement(emplacement);
        return "Réception bien enregistrée";
    }
    @GetMapping("/listPalettesToRece")
    public List<String> getListPalettesToRece() {
        List<Pallete> paletteList = palleteService.getPalleteList();
        List<String> list = new ArrayList<>();

        for (Pallete palette:paletteList) {
            if(palette.getFournisseurCmd() != null && palette.getFournisseurRsp() == null) {
                list.add(palette.getName());
            }
        }
        return list;
    }
    @GetMapping("/listFactures")
    public List<List<String>> getListFactures() {
        List<FournisseurFct> fournisseurFctList =  fournisseurMvtService.getFournisseurFctList();
        List<List<String>> list = new ArrayList<>();

        for (FournisseurFct Facture:fournisseurFctList) {
            List<String> listInfo = new ArrayList<>();
            listInfo.add(Integer.toString(Facture.getId()));
            listInfo.add(Facture.getPallete().getName());
            list.add(listInfo);
        }
        return list;
    }
    @GetMapping("/factureInfo/{id}")
    public List<String> getFactureInfo(@PathVariable int id) {
        FournisseurFct fournisseurFct = fournisseurMvtService.getFournisseurFctById(id);
        return fournisseurFct.getInfo();
    }
    @GetMapping("/factureFournisseur/{page}/{size}")
    public List<List<String>> getPageEmplacements(@PathVariable int page, @PathVariable int size){
        Page<FournisseurFct> factureFournisseurPage = fournisseurMvtService.getFournisseurFctPages(page,size);
        List<List<String>> list = new ArrayList<>();

        for (FournisseurFct fournisseurFct:factureFournisseurPage) {
            List<String> ligneFactFourInfo = new ArrayList<>();
            ligneFactFourInfo.add(Integer.toString(fournisseurFct.getId()));
            ligneFactFourInfo.add(fournisseurFct.getPallete().getName());
            list.add(ligneFactFourInfo);
        }
        return list;
    }

    @GetMapping("/factureFournisseur/totalPages/{page}/{size}")
    public int getTotalPagesEmplacements(@PathVariable int page, @PathVariable int size){
        return fournisseurMvtService.getFournisseurFctPages(page,size).getTotalPages();
    }
}
