package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Famille {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "famille",fetch = FetchType.LAZY)
    private List<Produit> produits;

    public List<String> getQuantityInfo() {
        List<String> list = new ArrayList<>();
        long qte = 0;
        for (Produit produit:produits){
            for (Pallete pallete:produit.getPalletes()) {
                if(pallete.getEmplacement() != null) {
                    qte+=pallete.getQuantity();
                }
            }
        }
        list.add(name);
        list.add(Long.toString(qte));
        return list;
    }
}
