package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Emplacement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String allee;
    private String nvHorizontal;
    private String nvVertical;
    @OneToOne
    @JoinColumn(name = "pallete_id", referencedColumnName = "id")
    private Pallete pallete;
    @ManyToOne
    private Depot depot;
    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(allee);
        list.add(nvHorizontal);
        list.add(nvVertical);
        list.add(depot.getName());
        return list;
    }

}
