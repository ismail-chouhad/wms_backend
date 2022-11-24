package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.DatePrd;
import com.chouhad.wms_backend.Repositories.DatePrdRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class DatePrdServiceImpl implements DatePrdService {
    DatePrdRepository datePrdRepository;
    @Override
    public DatePrd saveDatePrd(DatePrd datePrd) {
        return datePrdRepository.save(datePrd);
    }
    @Override
    public String getDatePrdDateById(int id){
        return datePrdRepository.findDatePrdDateById(id).getDate();
    }
    @Override
    public List<DatePrd> getDatePrdList(){
        return datePrdRepository.findAll();
    }
    @Override
    public Page<DatePrd> getDatePrdsPages(int page, int size){
        return datePrdRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public DatePrd getDatePrdByDate(String date){
        return datePrdRepository.findDatePrdByDate(date);
    }
    @Override
    public DatePrd getDatePrdById(int id){
        return datePrdRepository.findDatePrdById(id);
    }
    @Override
    public void deleteDatePrd(int id){
        datePrdRepository.deleteDatePrdById(id);
    }
}

