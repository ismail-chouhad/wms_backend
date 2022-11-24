package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class DateExp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    @OneToMany(mappedBy = "dateExp",fetch = FetchType.LAZY)
    private List<Produit> produits;
}
