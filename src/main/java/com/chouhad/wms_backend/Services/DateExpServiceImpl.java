package com.chouhad.wms_backend.Services;

import com.chouhad.wms_backend.Entities.DateExp;
import com.chouhad.wms_backend.Repositories.DateExpRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class DateExpServiceImpl implements DateExpService {
    DateExpRepository dateExpRepository;
    @Override
    public DateExp saveDateExp(DateExp dateExp) {
        return dateExpRepository.save(dateExp);
    }
    @Override
    public String getDateExpDateById(int id){
        return dateExpRepository.findDateExpDateById(id).getDate();
    }
    @Override
    public List<DateExp> getDateExpList(){
        return dateExpRepository.findAll();
    }
    @Override
    public Page<DateExp> getDateExpsPages(int page, int size){
        return dateExpRepository.findAll(PageRequest.of(page,size));
    }
    @Override
    public DateExp getDateExpByDate(String date){
        return dateExpRepository.findDateExpByDate(date);
    }
    @Override
    public DateExp getDateExpById(int id){
        return dateExpRepository.findDateExpById(id);
    }
    @Override
    public void deleteDateExp(int id){
        dateExpRepository.deleteDateExpById(id);
    }
}

