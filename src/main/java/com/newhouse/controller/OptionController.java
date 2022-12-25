package com.newhouse.controller;

import com.newhouse.model.entity.Option;

import com.newhouse.service.option.IOptionService;
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
    private IOptionService optionService;


    @GetMapping("")
    public ResponseEntity<?> findAllOption1(){
        Iterable<Option> option1ServiceAll = optionService.findAll();
        return new ResponseEntity<>(option1ServiceAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Option> saveOption(@ModelAttribute Option option) {
        Option ProductOption = optionService.save(option);
        return new ResponseEntity<>(optionService.save(ProductOption), HttpStatus.CREATED);
    }


    @PostMapping ("/{id}")
    public ResponseEntity<Option> update(@PathVariable Long id, @ModelAttribute Option productOption) {
        Optional<Option> ProductOption = optionService.findById(id);
        Option opt =ProductOption.get();
        if(!ProductOption.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        opt.setName(productOption.getName());
        opt.setPrice(productOption.getPrice());
        opt.setGroup(productOption.getGroup());
        return new ResponseEntity<>(optionService.save(opt), HttpStatus.CREATED);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<?> getOptionList(@PathVariable Long optionId){
        Optional<Option> ProductOption = optionService.findById(optionId);
        return new ResponseEntity<>(ProductOption, HttpStatus.OK);
    }

    @GetMapping("option-group/{optionGroupId}")
    public ResponseEntity<?> getOptionByOptionGroup(@PathVariable Long optionGroupId){
        Iterable<Option> ProductOptionList = optionService.getOptionByOptionGroup(optionGroupId.intValue());
        return new ResponseEntity<>(ProductOptionList, HttpStatus.OK);
    }

}
