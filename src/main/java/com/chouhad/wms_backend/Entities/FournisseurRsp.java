package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class FournisseurRsp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToOne
    @JoinColumn(name = "pallete_id",referencedColumnName = "id")
    private Pallete pallete;
}
