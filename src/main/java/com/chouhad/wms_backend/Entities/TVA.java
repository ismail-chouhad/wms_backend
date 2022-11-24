package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class TVA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float taux;
    @OneToMany(mappedBy = "tva",fetch = FetchType.LAZY)
    private List<Produit> produits;
}
