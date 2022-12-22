package com.newhouse.controller;

import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.dish.DishForm;
import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.category.CategoryDTO;
import com.newhouse.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<?> findAllCategories() {
        Iterable<CategoryDTO> categoryDTOs = categoryService.getAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/top-five")
    public ResponseEntity<?> findTopFiveCategories() {
        List<Category> top5Categories = categoryService.findTop5Categories();
        return new ResponseEntity<>(top5Categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@ModelAttribute CategoryDTO categoryDTO) {
        List<MultipartFile> img = categoryDTO.getImage();
        Category category = new Category();
        if (img != null && img.size() != 0) {
            long currentTime = System.currentTimeMillis();
            List<String> fileName = img.stream().map(x -> currentTime + x.getOriginalFilename()).collect(Collectors.toList());
            img.stream().forEach(y ->
            {
                try {
                    FileCopyUtils.copy(y.getBytes(), new File(uploadPath + currentTime + y.getOriginalFilename()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            category.setId(categoryDTO.getId());
            category.setImage(fileName.get(0));
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setNumberOfDishes(categoryDTO.getNumberOfDishes());
            categoryService.save(category);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
