package com.newhouse.controller.dish;

import com.newhouse.model.entity.Cart;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.dish.DishForm;
import com.newhouse.service.dish.IDishService;
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
@RequestMapping("api/dishes")
public class DishController {
    public final int ITEM_PER_PAGE = 12;

    @Autowired
    private IDishService dishService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Page<Dish>> showDishes(@RequestParam(name = "q") Optional<String> q, @PathVariable int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, ITEM_PER_PAGE);
        Page<Dish> dishes = dishService.findAll(pageable);
        if (q.isPresent()) {
            dishes = dishService.findAllByNameContaining(q.get(), pageable);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<?> findAll() {
        Iterable<Dish> dishAll = dishService.findAll();
        return new ResponseEntity<>(dishAll, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if(!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dish> saveDish(@ModelAttribute DishForm dishForm) {
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
            Dish dish = new Dish();
            dish.setId(dishForm.getId());
            dish.setName(dishForm.getName());
            dish.setCategories(dishForm.getCategories());
            dish.setPrice(dishForm.getPrice());
            dish.setDescription(dishForm.getDescription());
            dish.setOptionOfProduct(dishForm.getOptionOfProduct());
            dish.setSpecifications(dishForm.getSpecifications());
            dish.setSold(Long.valueOf(0));
            for (int i = fileName.size(); i <8; i++) {
                fileName.add(null);
            }
            if(fileName.get(0) !=null) {
                dish.setImage01(fileName.get(0));
            }
            if(fileName.get(1) !=null) {
                dish.setImage02(fileName.get(1));
            }
            if(fileName.get(2) !=null) {
                dish.setImage03(fileName.get(2));
            }
            if(fileName.get(3) !=null) {
                dish.setImage04(fileName.get(3));
            }
            if(fileName.get(4) !=null) {
                dish.setImage05(fileName.get(4));
            }
            if(fileName.get(5) !=null) {
                dish.setImage06(fileName.get(5));
            }
            if(fileName.get(6) !=null) {
                dish.setImage07(fileName.get(6));
            }
            if(fileName.get(7) !=null) {
                dish.setImage08(fileName.get(7));
            }

            return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/most-purchased/{top}")
    public ResponseEntity<?> getMostPurchasedDishes(@PathVariable Long top){
        if (top == null) top = 8L;
        Iterable<Dish> dishes = dishService.findMostPurchased(top.intValue());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }


    @GetMapping("/{dishId}/top-{limit}-same-category")
    public ResponseEntity<?> findDishesWithSameCategoryWith(@PathVariable Long dishId, @PathVariable int limit){
        Iterable<Dish> dishes =  dishService.findDishesWithSameCategoryWith(dishId, limit);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("category/{category_id}")
    public ResponseEntity<?> findDishesbyCategory(@PathVariable Long category_id){
        Iterable<Dish> dishes =  dishService.findDishesbyCategoryID(category_id.intValue());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}