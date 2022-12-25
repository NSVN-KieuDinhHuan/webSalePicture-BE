package com.newhouse.service.category;

import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.category.CategoryDto;
import com.newhouse.repository.ICategoryRepository;
import com.newhouse.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductService dishService;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<CategoryDto> getAllCategoryDTO() {
        Iterable<Category> categories = findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        categories.forEach(
                category -> {
                    int count = dishService.countDishByCategoriesIsContaining(category);
                    CategoryDto categoryDTO = new CategoryDto();
                    categoryDTO.setId(category.getId());
                    categoryDTO.setName(category.getName());
                    categoryDTO.setDescription(category.getDescription());
                    categoryDTO.setImage(category.getImage());
                    categoryDTO.setNumberOfDishes(count);
                    categoryDtos.add(categoryDTO);
                }
        );
        return categoryDtos;
    }

    @Override
    public List<Category> findTop5Categories() {
        return categoryRepository.findTop5Categories();
    }
}
