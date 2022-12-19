package com.newhouse.service.dish;


import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishService extends IGeneralService<Dish> {
    Page<Dish> findAll(Pageable pageable);

    Page<Dish> findAllByNameContaining(String name, Pageable pageable);

    int countDishByCategoriesIsContaining(Category category);

    Iterable<Dish> findMostPurchased(int top);

    Iterable<Dish> findDishesbyCategoryID(int categoryId);
    Iterable<Dish> findDishesWithSameCategoryWith(Long dishId, int limit);
}
