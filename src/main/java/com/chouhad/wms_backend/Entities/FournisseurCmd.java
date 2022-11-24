package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class FournisseurCmd {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToOne
    @JoinColumn(name = "pallete_id",referencedColumnName = "id")
    private Pallete pallete ;
    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(fournisseur.getName());
        list.add(pallete.getName());
        return list;
    }
}
