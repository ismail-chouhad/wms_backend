package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class FournisseurCategorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "fournisseurCategorie",fetch = FetchType.LAZY)
    private List<Fournisseur> fournisseurs;
}

