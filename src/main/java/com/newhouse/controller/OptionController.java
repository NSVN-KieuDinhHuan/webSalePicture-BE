package com.newhouse.controller;

import com.newhouse.model.entity.ProductOption;

import com.newhouse.service.option.IProductOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/option")
public class OptionController {
    @Autowired
    private IProductOptService productOptService;


    @GetMapping("")
    public ResponseEntity<?> findAllOption1(){
        Iterable<ProductOption> option1ServiceAll = productOptService.findAll();
        return new ResponseEntity<>(option1ServiceAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductOption> saveOption(@ModelAttribute ProductOption productOption) {
        ProductOption ProductOption =productOptService.save(productOption);
        new ResponseEntity<>(productOptService.save(ProductOption), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<?> getOptionList(@PathVariable Long optionId){
        Optional<ProductOption> ProductOption = productOptService.findById(optionId);
        return new ResponseEntity<>(ProductOption, HttpStatus.OK);
    }
}
