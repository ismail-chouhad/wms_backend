package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.*;
import com.chouhad.wms_backend.Services.EmplacementService;
import com.chouhad.wms_backend.Services.ClientMvtService;
import com.chouhad.wms_backend.Services.ClientService;
import com.chouhad.wms_backend.Services.PalleteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class ClientMvtRestAPI {
    private ClientMvtService clientMvtService;
    private PalleteService palleteService;
    private ClientService clientService;
    private EmplacementService emplacementService;

    @PostMapping("/clientCmds/save/{clientName}/{palleteName}")
    public String postCmdClient(@PathVariable String clientName, @PathVariable String palleteName) {
        Pallete pallete = palleteService.getPalleteByName(palleteName);
        Client client = clientService.getClientByName(clientName);

        if(pallete.getClientCmd()!=null && pallete.getClientCmd().getClient().getName().equals(clientName)) {
            return "Cette pallete est déjà enregistrée dans une commande";
        }

        ClientCmd clientCmd = new ClientCmd();

        clientCmd.setClient(client);
        clientCmd.setPallete(pallete);
        clientMvtService.saveClientCmd(clientCmd);

        pallete.setClientCmd(clientCmd);
        palleteService.savePallete(pallete);

        return "Commande bien enregistrée";
    }
    @GetMapping("/listPalettesToCmdClient")
    public List<List<String>> getListPalettesToCmdClient() {
        List<Pallete> paletteList = palleteService.getPalleteList();
        List<List<String>> list = new ArrayList<>();

        for (Pallete palette:paletteList) {
            if(palette.getClientCmd() == null) {
                list.add(palette.getInfo());
            }
        }
        return list;
    }

    @PostMapping("/clientLvrs/save/{clientCmdId}")
    public String postLvrClient(@PathVariable int clientCmdId) {
        ClientCmd clientCmd = clientMvtService.getClientCmdById(clientCmdId);
        Pallete pallete = clientCmd.getPallete();
        Emplacement emplacement = pallete.getEmplacement();
        Client client = clientCmd.getClient();

        ClientLvr clientLvr = new ClientLvr();
        ClientFct clientFct = new ClientFct();

        clientLvr.setClient(client);
        clientLvr.setPallete(pallete);
        clientMvtService.saveClientLvr(clientLvr);

        clientFct.setClient(client);
        clientFct.setPallete(pallete);
        clientMvtService.saveClientFct(clientFct);

        pallete.setClientLvr(clientLvr);
        pallete.setClientFct(clientFct);
        pallete.setEmplacement(emplacement);
        palleteService.savePallete(pallete);

        emplacement.setPallete(pallete);
        emplacementService.saveEmplacement(emplacement);
        return "Livraison bien enregistrée";
    }
    @GetMapping("/listPalettesToLiv")
    public List<List<String>> getListPalettesToLiv() {
        List<Pallete> paletteList = palleteService.getPalleteList();
        List<List<String>> list = new ArrayList<>();

        for (Pallete pallete:paletteList) {
            if(pallete.getClientCmd() == null && pallete.getEmplacement() != null && pallete.getFournisseurCmd() != null ) {
                list.add(pallete.getInfo());
            }
        }
        return list;
    }
    @GetMapping("/listFacturesClient")
    public List<List<String>> getListFactures() {
        List<ClientFct> clientFctList =  clientMvtService.getClientFctList();
        List<List<String>> list = new ArrayList<>();

        for (ClientFct clientFct:clientFctList) {
            List<String> listInfo = new ArrayList<>();
            listInfo.add(Integer.toString(clientFct.getId()));
            listInfo.add(clientFct.getPallete().getName());
            list.add(listInfo);
        }
        return list;
    }
    @GetMapping("/factureClientInfo/{id}")
    public List<String> getFactureInfo(@PathVariable int id) {
        ClientFct clientFct = clientMvtService.getClientFctById(id);
        return clientFct.getInfo();
    }
    @GetMapping("/CmdClient/{page}/{size}")
    public List<List<String>> getCmdPageEmplacements(@PathVariable int page, @PathVariable int size){
        Page<ClientCmd> CmdClientPage = clientMvtService.getClientCmdPages(page,size);
        List<List<String>> list = new ArrayList<>();

        for (ClientCmd clientCmd:CmdClientPage) {
            List<String> cmdClientList = new ArrayList<>();
            cmdClientList.add(Integer.toString(clientCmd.getId()));
            cmdClientList.add(clientCmd.getPallete().getName());
            list.add(cmdClientList);
        }
        return list;
    }
    @GetMapping("/CmdClient/totalPages/{page}/{size}")
    public int getTotalCmdPagesEmplacements(@PathVariable int page, @PathVariable int size){
        return clientMvtService.getClientCmdPages(page,size).getTotalPages();
    }


    @GetMapping("/factureClient/{page}/{size}")
    public List<List<String>> getFacturePageEmplacements(@PathVariable int page, @PathVariable int size){
        Page<ClientFct> factureClientPage = clientMvtService.getClientFctPages(page,size);
        List<List<String>> list = new ArrayList<>();

        for (ClientFct clientFct:factureClientPage) {
            List<String> factureClientList = new ArrayList<>();
            factureClientList.add(Integer.toString(clientFct.getId()));
            factureClientList.add(clientFct.getPallete().getName());
            list.add(factureClientList);
        }
        return list;
    }
    @GetMapping("/factureClient/totalPages/{page}/{size}")
    public int getTotalFacturePagesEmplacements(@PathVariable int page, @PathVariable int size){
        return clientMvtService.getClientFctPages(page,size).getTotalPages();
    }
}
