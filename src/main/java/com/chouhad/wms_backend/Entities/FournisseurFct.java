package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class FournisseurFct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToOne
    @JoinColumn(name = "pallete_id",referencedColumnName = "id")
    private Pallete pallete;

    public List<String> getInfo() {
        long paletteQty = pallete.getQuantity();
        float unitPrice = pallete.getProduit().getPriceUnit();
        float totalPrice = paletteQty * unitPrice;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        List<String> list = new ArrayList<>();

        list.add(Integer.toString(pallete.getFournisseurRsp().getId()));//0
        list.add("Réception du palette :  "+pallete.getName());//1
        list.add(fournisseur.getName());//2
        list.add(pallete.getProduit().getName());//3
        list.add(pallete.getProduit().getMarque().getName());//4
        list.add(pallete.getProduit().getFamille().getName());//5
        list.add(pallete.getProduit().getDatePrd().getDate());//6
        list.add(pallete.getProduit().getDateExp().getDate());//7
        list.add(Long.toString(paletteQty));//8
        list.add(Float.toString(unitPrice)+" DH");//9
        list.add(Float.toString(totalPrice)+" DH");//10
        list.add(fournisseur.getEmail());//11
        list.add(fournisseur.getNum());//12
        list.add(fournisseur.getAddress());//13
        list.add(dateFormat.format(date));//14

        return list;
    }
}
