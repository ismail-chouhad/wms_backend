package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.ClientCmd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCmdRepository extends JpaRepository<ClientCmd,Integer> {
    ClientCmd findClientCmdById(int id);
}
