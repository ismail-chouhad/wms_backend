package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.DatePrd;
import com.chouhad.wms_backend.Entities.DatePrd;
import com.chouhad.wms_backend.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatePrdRepository extends JpaRepository<DatePrd,Integer> {
    DatePrd findDatePrdById(int id);
    DatePrd findDatePrdDateById(int id);
    DatePrd findDatePrdByDate(String date);
    void deleteDatePrdById(int id);
}
