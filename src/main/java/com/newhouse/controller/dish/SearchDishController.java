package com.newhouse.controller.dish;

import com.newhouse.model.dto.search_form.SearchForm;
import com.newhouse.model.entity.dish.Product;
import com.newhouse.service.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/dishes")
public class SearchDishController {
    @Autowired
    SearchProductService searchProductService;

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchForm searchForm){
        Iterable<Product> dishes =  searchProductService.searchByForm(searchForm);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

}
