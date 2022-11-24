package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.DatePrd;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DatePrdService {
    DatePrd saveDatePrd(DatePrd datePrd);

    String getDatePrdDateById(int id);

    List<DatePrd> getDatePrdList();

    Page<DatePrd> getDatePrdsPages(int page, int size);

    DatePrd getDatePrdByDate(String date);

    DatePrd getDatePrdById(int id);

    void deleteDatePrd(int id);
}
