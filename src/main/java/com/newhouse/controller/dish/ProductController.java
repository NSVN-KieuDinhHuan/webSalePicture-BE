package com.newhouse.controller.dish;

import com.newhouse.model.entity.dish.Product;
import com.newhouse.model.entity.dish.ProductForm;
import com.newhouse.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {
    public final int ITEM_PER_PAGE = 12;

    @Autowired
    private IProductService dishService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Page<Product>> showDishes(@RequestParam(name = "q") Optional<String> q, @PathVariable int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, ITEM_PER_PAGE);
        Page<Product> dishes = dishService.findAll(pageable);
        if (q.isPresent()) {
            dishes = dishService.findAllByNameContaining(q.get(), pageable);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        Iterable<Product> dishAll = dishService.findAll();
        return new ResponseEntity<>(dishAll, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findDishById(@PathVariable Long id) {
        Optional<Product> dishOptional = dishService.findById(id);
        if(!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        Optional<Product> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveDish(@ModelAttribute ProductForm dishForm) {
        List<MultipartFile> img = dishForm.getImage();
        if (img != null && img.size() != 0) {
            long currentTime = System.currentTimeMillis();
            List<String> fileName= img.stream().map(x->currentTime+x.getOriginalFilename()).collect(Collectors.toList());
                img.stream().forEach(y ->
                {
                    try {
                        FileCopyUtils.copy(y.getBytes(), new File(uploadPath + currentTime+y.getOriginalFilename()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            Product product = new Product();
            product.setId(dishForm.getId());
            product.setName(dishForm.getName());
            product.setCategories(dishForm.getCategories());
            product.setPrice(dishForm.getPrice());
            product.setDescription(dishForm.getDescription());
            product.setOptionGroups(dishForm.getOptionGroups());
            product.setSpecifications(dishForm.getSpecifications());
            product.setSold(Long.valueOf(0));
            for (int i = fileName.size(); i <8; i++) {
                fileName.add(null);
            }
            if(fileName.get(0) !=null) {
                product.setImage01(fileName.get(0));
            }
            if(fileName.get(1) !=null) {
                product.setImage02(fileName.get(1));
            }
            if(fileName.get(2) !=null) {
                product.setImage03(fileName.get(2));
            }
            if(fileName.get(3) !=null) {
                product.setImage04(fileName.get(3));
            }
            if(fileName.get(4) !=null) {
                product.setImage05(fileName.get(4));
            }
            if(fileName.get(5) !=null) {
                product.setImage06(fileName.get(5));
            }
            if(fileName.get(6) !=null) {
                product.setImage07(fileName.get(6));
            }
            if(fileName.get(7) !=null) {
                product.setImage08(fileName.get(7));
            }

            return new ResponseEntity<>(dishService.save(product), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/most-purchased/{top}")
    public ResponseEntity<?> getMostPurchasedDishes(@PathVariable Long top){
        if (top == null) top = 8L;
        Iterable<Product> dishes = dishService.findMostPurchased(top.intValue());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }


    @GetMapping("/{dishId}/top-{limit}-same-category")
    public ResponseEntity<?> findDishesWithSameCategoryWith(@PathVariable Long dishId, @PathVariable int limit){
        Iterable<Product> dishes =  dishService.findDishesWithSameCategoryWith(dishId, limit);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("category/{category_id}")
    public ResponseEntity<?> findDishesbyCategory(@PathVariable Long category_id){
        Iterable<Product> dishes =  dishService.findDishesbyCategoryID(category_id.intValue());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}