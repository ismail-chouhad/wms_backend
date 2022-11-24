package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long barCode;
    private String name;
    private float priceUnit;
    private float priceHT;
    private float priceTTC;
    @ManyToOne
    private Marque marque;
    @ManyToOne
    private Famille famille;
    @ManyToOne
    private TVA tva;
    @ManyToOne
    private DatePrd datePrd;
    @ManyToOne
    private DateExp dateExp;

    @OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
    private List<Pallete> palletes;


    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(Long.toString(barCode));
        list.add(name);
        list.add(String.format("%.2f",priceUnit));
        list.add(String.format("%.2f",priceHT));
        list.add(String.format("%.2f",priceTTC));
        list.add(marque.getName());
        list.add(famille.getName());
        list.add(String.format("%.2f",tva.getTaux()));
        list.add(datePrd.getDate());
        list.add(dateExp.getDate());
        return list;
    }

    public List<String> getQuantityInfo() {
        List<String> list = new ArrayList<>();
        long qte = 0;
        for (Pallete pallete:palletes) {
            if(pallete.getEmplacement() != null) {
                qte+=pallete.getQuantity();
            }
        }
        list.add(name);
        list.add(Long.toString(qte));
        return list;
    }

}
