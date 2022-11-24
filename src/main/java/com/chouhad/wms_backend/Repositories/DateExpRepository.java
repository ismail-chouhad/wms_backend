package com.chouhad.wms_backend.Repositories;

import com.chouhad.wms_backend.Entities.DateExp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateExpRepository extends JpaRepository<DateExp,Integer> {
    DateExp findDateExpById(int id);
    DateExp findDateExpDateById(int id);
    DateExp findDateExpByDate(String date);
    void deleteDateExpById(int id);
}
