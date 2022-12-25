package com.newhouse.service.product;


import com.newhouse.model.entity.dish.Product;
import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    int countDishByCategoriesIsContaining(Category category);

    Iterable<Product> findMostPurchased(int top);

    Iterable<Product> findDishesbyCategoryID(int categoryId);
    Iterable<Product> findDishesWithSameCategoryWith(Long dishId, int limit);
}
