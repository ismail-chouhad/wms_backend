package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.DateExp;
import com.chouhad.wms_backend.Services.DateExpService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class DateExpRestAPI {
    private DateExpService dateExpService;

    @GetMapping("/dateExps")
    public List<DateExp> dateExpList() {
        return dateExpService.getDateExpList();
    }
    @GetMapping("/dateExps/{id}")
    public DateExp getDateExp(@PathVariable int id){
        return dateExpService.getDateExpById(id);
    }

    @GetMapping("/dateExps/getId/{date}")
    public int getDateExpIdByDate(@PathVariable String date){
        DateExp dateExpByDate = dateExpService.getDateExpByDate(date);
        if(dateExpByDate != null) {
            return dateExpByDate.getId();
        }
        return -1;
    }

    @GetMapping("/dateExps/{page}/{size}")
    public List<String> getDateExp(@PathVariable int page, @PathVariable int size){
        Page<DateExp> dateExpPage = dateExpService.getDateExpsPages(page,size);
        List<String> dateExpsDate= new ArrayList<>();
        dateExpPage.forEach(dateExp-> dateExpsDate.add(dateExp.getDate()));
        return dateExpsDate;
    }
    @GetMapping("/dateExps/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return dateExpService.getDateExpsPages(page,size).getTotalPages();
    }
    @GetMapping("/dateExps/getAllDateExpsDates")
    public List<String> getListDateExpsDates(){
        List<DateExp> dateExpList = dateExpService.getDateExpList();
        List<String> listDates = new ArrayList<>();
        dateExpList.forEach(dateExp ->  {listDates.add(dateExp.getDate());});
        return listDates;
    }
    @PostMapping("/dateExps/save/{date}")
    public String addDateExp(@PathVariable String date){
        DateExp dateExp = dateExpService.getDateExpByDate(date);
        if(dateExp == null){
            DateExp dateExp1 = new DateExp();
            dateExp1.setDate(date);
            dateExpService.saveDateExp(dateExp1);
            return "DateExp ajoutée";
        }
        return "DateExp existe déjà";
    }

    @PutMapping("/dateExps/edit/{id}/{date}")
    public String updateDateExp(@PathVariable int id, @PathVariable String date){
        DateExp dateExp = dateExpService.getDateExpById(id);
        DateExp dateExpByDate = dateExpService.getDateExpByDate(date);
        if(dateExp != null && dateExpByDate == null){
            dateExp.setDate(date);
            dateExpService.saveDateExp(dateExp);
            return "DateExp modifiée";
        }
        return "DateExp non modifiée";
    }
    @DeleteMapping("/dateExps/delete/{id}")
    public void deleteDateExp(@PathVariable int id){
        dateExpService.deleteDateExp(id);
    }


}
