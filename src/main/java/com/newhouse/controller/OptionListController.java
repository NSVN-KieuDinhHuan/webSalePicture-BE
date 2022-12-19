package com.newhouse.controller;

import com.newhouse.model.dto.option.OptionListDTO;
import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.ProductOptionList;
import com.newhouse.service.option.IProductOptService;
import com.newhouse.service.option.optionList.IProductOptListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/optionlist")
public class OptionListController {
    @Autowired
    private IProductOptListService productOptListService;
    @Autowired
    private IProductOptService productOptService;

    @GetMapping("")
    public ResponseEntity<?> findAllOption1(){
        Iterable<ProductOptionList> ProductOptionServiceAll = productOptListService.findAll();
        return new ResponseEntity<>(ProductOptionServiceAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductOptionList> CreateOptionList (@ModelAttribute OptionListDTO optionListDTO) {
        ProductOptionList productOptionList =new ProductOptionList();
        productOptionList.setName(optionListDTO.getName());
        new ResponseEntity<>(productOptListService.save(productOptionList), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{optionListid}/{optionid}")
    public ResponseEntity<ProductOptionList> addOption (@PathVariable Long optionListid,@PathVariable Long optionid) {
        Optional<ProductOptionList> productOptionList =productOptListService.findById(optionListid);
        Optional<ProductOption> productOption =productOptService.findById(optionid);
        productOptionList.get().getOptionList().add(productOption.get());
        new ResponseEntity<>(productOptListService.save(productOptionList.get()), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{optionListid}")
    public ResponseEntity<?> getOptionList(@PathVariable Long optionListid){
        Optional<ProductOptionList> ProductOptionList = productOptListService.findById(optionListid);
        return new ResponseEntity<>(ProductOptionList, HttpStatus.OK);
    }

}
