package com.newhouse.service.product;


import com.newhouse.model.entity.dish.Product;
import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.repository.dish.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public int countDishByCategoriesIsContaining(Category category) {
        return productRepository.countDishByCategoriesIsContaining(category);
    }

    @Override
    public Iterable<Product> findMostPurchased(int top) {
        return productRepository.findTopPurchased(top);
    }

    @Override
    public Iterable<Product> findDishesbyCategoryID(int categoryId) {
        Iterable<Product> dish =  productRepository.findDishesbyCategoryID(categoryId);
        return dish;
    }


    @Override
    public Iterable<Product> findDishesWithSameCategoryWith(Long dishId, int limit) {
        return productRepository.findDishesWithSameCategoryWith(dishId, limit);
    }
}
