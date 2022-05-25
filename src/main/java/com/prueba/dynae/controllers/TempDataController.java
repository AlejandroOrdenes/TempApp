package com.prueba.dynae.controllers;

import com.prueba.dynae.dto.DataResultDTO;
import com.prueba.dynae.dto.FormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.prueba.dynae.service.TempDataService;

import java.text.ParseException;

@Controller
@RequestMapping("/temp")
public class TempDataController {

    @Autowired
    private TempDataService tempDataService;


    @RequestMapping(value = "/getData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json")
    @ResponseBody
    public  DataResultDTO getData(@RequestBody FormDTO valuesForm) throws ParseException {
        return tempDataService.getData(valuesForm);
    }


    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


   




}
