package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Depot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "depot",fetch = FetchType.LAZY)
    private List<Emplacement> emplacements;
    @ManyToOne
    private Ville ville;

    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(address);
        list.add(ville.getName());
        return list;
    }

    public List<String> getQuantityInfo() {
        List<String> list = new ArrayList<>();
        long qte = 0;
        for (Emplacement emplacement:emplacements) {
            if(emplacement.getPallete() != null) {
                Pallete pallete = emplacement.getPallete();
                qte+=pallete.getQuantity();
            }
        }
        list.add(name);
        list.add(Long.toString(qte));
        return list;
    }
}
