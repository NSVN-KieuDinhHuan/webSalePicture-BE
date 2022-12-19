package com.newhouse.service.category;

import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.category.CategoryDTO;
import com.newhouse.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    Iterable<CategoryDTO> getAllCategoryDTO();


    List<Category> findTop5Categories();
}