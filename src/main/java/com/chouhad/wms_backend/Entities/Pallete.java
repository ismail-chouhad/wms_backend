package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pallete {
//  Pallete_info
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String numSerie;
    private long quantity;
//   Fournisseur
    @OneToOne
    @JoinColumn(name = "fournisseur_cmd_Id",referencedColumnName = "id")
    private FournisseurCmd fournisseurCmd;
    @OneToOne
    @JoinColumn(name = "fournisseur_rsp_Id",referencedColumnName = "id")
    private FournisseurRsp fournisseurRsp;
    @OneToOne
    @JoinColumn(name = "fournisseur_fct_Id",referencedColumnName = "id")
    private FournisseurFct fournisseurFct;
//   Client
    @OneToOne
    @JoinColumn(name = "client_cmd_Id",referencedColumnName = "id")
    private ClientCmd clientCmd;
    @OneToOne
    @JoinColumn(name = "client_rsp_Id",referencedColumnName = "id")
    private ClientLvr clientLvr;
    @OneToOne
    @JoinColumn(name = "client_fct_Id",referencedColumnName = "id")
    private ClientFct clientFct;
//   Emplacement
    @OneToOne
    @JoinColumn(name = "emplacement_id", referencedColumnName = "id")
    private Emplacement emplacement;
//   Pallete
    @ManyToOne
    private Produit produit;

    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(numSerie);
        list.add(Long.toString(quantity));
        list.add(produit.getName());
        return list;
    }

}
