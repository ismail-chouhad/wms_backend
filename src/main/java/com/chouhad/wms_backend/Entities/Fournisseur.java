package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String num;
    private String address;
    @ManyToOne
    private FournisseurCategorie fournisseurCategorie;
    @OneToMany(mappedBy = "fournisseur",fetch = FetchType.LAZY)
    private List<FournisseurCmd> fournisseurCmds;
    @OneToMany(mappedBy = "fournisseur",fetch = FetchType.LAZY)
    private List<FournisseurRsp> fournisseurRsps ;
    @OneToMany(mappedBy = "fournisseur",fetch = FetchType.LAZY)
    private List<FournisseurFct> fournisseurFcts;

    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(email);
        list.add(num);
        list.add(address);
        list.add(fournisseurCategorie.getName());
        return list;
    }

    public List<String> getQuantityInfo() {
        List<String> list = new ArrayList<>();
        int qte = 0;

        for (FournisseurFct fournisseurFct:fournisseurFcts) {
            Pallete pallete = fournisseurFct.getPallete();
            if(pallete.getEmplacement() != null) {
                qte+=pallete.getQuantity();
            }
        }

        list.add(name);
        list.add(Integer.toString(qte));
        return list;
    }
}
