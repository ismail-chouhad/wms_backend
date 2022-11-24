package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.DateExp;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DateExpService {
    DateExp saveDateExp(DateExp dateExp);

    String getDateExpDateById(int id);

    List<DateExp> getDateExpList();

    Page<DateExp> getDateExpsPages(int page, int size);

    DateExp getDateExpByDate(String date);

    DateExp getDateExpById(int id);

    void deleteDateExp(int id);
}
