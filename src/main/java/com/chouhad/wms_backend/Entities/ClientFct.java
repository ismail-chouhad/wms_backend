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
public class ClientFct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Client client;
    @OneToOne
    @JoinColumn(name = "pallete_id",referencedColumnName = "id")
    private Pallete pallete;

    public List<String> getInfo() {
        long palleteQte = pallete.getQuantity();
        float prdtPrixTTC = pallete.getProduit().getPriceTTC();
        float totalPrix = palleteQte * prdtPrixTTC;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        List<String> list = new ArrayList<>();

        list.add(Integer.toString(pallete.getFournisseurRsp().getId()));//0
        list.add("Livraison du palette "+pallete.getName());//1
        list.add(client.getName());//2
        list.add(pallete.getProduit().getName());//3
        list.add(pallete.getProduit().getMarque().getName());//4
        list.add(pallete.getProduit().getFamille().getName());//5
        list.add(pallete.getProduit().getDatePrd().getDate());//6
        list.add(pallete.getProduit().getDateExp().getDate());//7
        list.add(Long.toString(palleteQte));//8
        list.add(Float.toString(prdtPrixTTC)+" DH");//9
        list.add(Float.toString(totalPrix)+" DH");//10
        list.add(client.getEmail());//11
        list.add(client.getNum());//12
        list.add(client.getAddress());//13
        list.add(dateFormat.format(date));//14

        return list;
    }
}
