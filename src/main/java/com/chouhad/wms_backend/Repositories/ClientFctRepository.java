package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.ClientFct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientFctRepository extends JpaRepository<ClientFct,Integer> {
    ClientFct findClientFctById(int id);
}
