package com.newhouse.controller;

import com.newhouse.model.dto.option.OptionListDTO;
import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.OptionGroup;
import com.newhouse.service.option.IOptionService;
import com.newhouse.service.option.optionGroup.IOptGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/option-group")
public class OptionListController {
    @Autowired
    private IOptGroupService optGroupService;
    @Autowired
    private IOptionService productOptService;

    @GetMapping("")
    public ResponseEntity<?> findAllOption1(){
        Iterable<OptionGroup> ProductOptionServiceAll = optGroupService.findAll();
        return new ResponseEntity<>(ProductOptionServiceAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OptionGroup> CreateOptionList (@ModelAttribute OptionListDTO optionListDTO) {
        OptionGroup optionGroup =new OptionGroup();
        optionGroup.setName(optionListDTO.getName());
        return  new ResponseEntity<>(optGroupService.save(optionGroup), HttpStatus.CREATED);
    }

    @GetMapping("/{optionGroupId}")
    public ResponseEntity<?> optionGroupById(@PathVariable Long optionGroupId){
        Optional<OptionGroup> ProductOptionList = optGroupService.findById(optionGroupId);
        return new ResponseEntity<>(ProductOptionList, HttpStatus.OK);
    }

}
