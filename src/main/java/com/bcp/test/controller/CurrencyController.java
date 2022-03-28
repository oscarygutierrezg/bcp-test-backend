package com.bcp.test.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bcp.test.dto.currency.CurrencyDto;
import com.bcp.test.service.CurrencyService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/v1/currency")
@CrossOrigin
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

  
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<List<CurrencyDto>>>  getAllCurrencies() {
        return currencyService.getAll()
                .subscribeOn(Schedulers.io())
                .map(currencyResponses -> ResponseEntity.ok().body(currencyResponses));
    }



    @GetMapping(
            value = "/{currencyId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public  Single<ResponseEntity<CurrencyDto>>  getCurrencyDetail(@PathVariable(value = "currencyId") UUID currencyId) {
        return currencyService.show(currencyId)
                .subscribeOn(Schedulers.io())
                .map(currencyResponse -> ResponseEntity.ok().body(currencyResponse));
    }

   
}
