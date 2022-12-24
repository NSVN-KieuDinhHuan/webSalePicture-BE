package com.newhouse.controller;

import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.category.CategoryDto;
import com.newhouse.model.entity.dish.category.CategoryForm;
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
import java.util.Optional;
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
        Iterable<CategoryDto> categoryDTOs = categoryService.getAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/top-five")
    public ResponseEntity<?> findTopFiveCategories() {
        List<Category> top5Categories = categoryService.findTop5Categories();
        return new ResponseEntity<>(top5Categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOptionList(@PathVariable Long id){
        Optional<Category> ProductOption = categoryService.findById(id);
        return new ResponseEntity<>(ProductOption, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Optional<Category> dishOptional = categoryService.findById(id);
        if (!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@ModelAttribute CategoryForm categoryForm) {
        List<MultipartFile> img = categoryForm.getImage();
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
            category.setId(categoryForm.getId());
            category.setImage(fileName.get(0));
            category.setName(categoryForm.getName());
            category.setDescription(categoryForm.getDescription());
            category.setNumberOfDishes(categoryForm.getNumberOfDishes());
            categoryService.save(category);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<Category> updateCategory(@ModelAttribute CategoryForm categoryForm,@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        List<MultipartFile> img = categoryForm.getImage();
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category category = categoryOptional.get();
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
            category.setId(categoryForm.getId());
            category.setImage(fileName.get(0));
            category.setName(categoryForm.getName());
            category.setDescription(categoryForm.getDescription());
            category.setNumberOfDishes(categoryForm.getNumberOfDishes());
            categoryService.save(category);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
