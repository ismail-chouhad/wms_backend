package com.chouhad.wms_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String num;
    private String address;
    @ManyToOne
    private ClientCategorie clientCategorie;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<ClientCmd> clientCmds;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<ClientLvr> clientLvrs;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<ClientFct> clientFcts;
    public List<String> getInfo() {
        List<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(email);
        list.add(num);
        list.add(address);
        list.add(clientCategorie.getName());
        return list;
    }
}
