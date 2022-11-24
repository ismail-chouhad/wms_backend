package com.chouhad.wms_backend.Web;

import com.chouhad.wms_backend.Entities.DatePrd;
import com.chouhad.wms_backend.Services.DatePrdService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class DatePrdRestAPI {
    private DatePrdService datePrdService;

    @GetMapping("/datePrds")
    public List<DatePrd> datePrdList() {
        return datePrdService.getDatePrdList();
    }
    @GetMapping("/datePrds/{id}")
    public DatePrd getDatePrd(@PathVariable int id){
        return datePrdService.getDatePrdById(id);
    }

    @GetMapping("/datePrds/getId/{date}")
    public int getDatePrdIdByDate(@PathVariable String date){
        DatePrd datePrdByDate = datePrdService.getDatePrdByDate(date);
        if(datePrdByDate != null) {
            return datePrdByDate.getId();
        }
        return -1;
    }

    @GetMapping("/datePrds/{page}/{size}")
    public List<String> getDatePrd(@PathVariable int page, @PathVariable int size){
        Page<DatePrd> datePrdPage = datePrdService.getDatePrdsPages(page,size);
        List<String> datePrdsDate= new ArrayList<>();
        datePrdPage.forEach(datePrd-> datePrdsDate.add(datePrd.getDate()));
        return datePrdsDate;
    }
    @GetMapping("/datePrds/getTotalPages/{page}/{size}")
    public int getTotalPages(@PathVariable int page, @PathVariable int size){
        return datePrdService.getDatePrdsPages(page,size).getTotalPages();
    }
    @GetMapping("/datePrds/getAllDatePrdsDates")
    public List<String> getListDatePrdsDates(){
        List<DatePrd> datePrdList = datePrdService.getDatePrdList();
        List<String> listDates = new ArrayList<>();
        datePrdList.forEach(datePrd ->  {listDates.add(datePrd.getDate());});
        return listDates;
    }
    @PostMapping("/datePrds/save/{date}")
    public String addDatePrd(@PathVariable String date){
        DatePrd datePrd = datePrdService.getDatePrdByDate(date);
        if(datePrd == null){
            DatePrd datePrd1 = new DatePrd();
            datePrd1.setDate(date);
            datePrdService.saveDatePrd(datePrd1);
            return "DatePrd ajoutée";
        }
        return "DatePrd existe déjà";
    }

    @PutMapping("/datePrds/edit/{id}/{date}")
    public String updateDatePrd(@PathVariable int id, @PathVariable String date){
        DatePrd datePrd = datePrdService.getDatePrdById(id);
        DatePrd datePrdByDate = datePrdService.getDatePrdByDate(date);
        if(datePrd != null && datePrdByDate == null){
            datePrd.setDate(date);
            datePrdService.saveDatePrd(datePrd);
            return "DatePrd modifiée";
        }
        return "DatePrd non modifiée";
    }
    @DeleteMapping("/datePrds/delete/{id}")
    public void deleteDatePrd(@PathVariable int id){
        datePrdService.deleteDatePrd(id);
    }


}
