package com.newhouse.service.product;

import com.newhouse.model.dto.search_form.SearchForm;
import com.newhouse.model.entity.dish.Product;
import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.repository.dish.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService {
    @Autowired
    private IProductRepository dishRepository;

    public Iterable<Product> findAllDishes(int limit){
        return dishRepository.findAllDishes(limit);
    }

    public Iterable<Product> searchByForm(SearchForm searchForm) {
        if (searchForm.getCategories().size() == 0) {
            return searchByNameOnly(searchForm.getQ(), searchForm.getLimit());
        }
        if (searchForm.getQ().isEmpty()){
            return searchByCategoriesOnly(searchForm.getCategories(), searchForm.getLimit());
        }
        return searchByNameAndCategories(searchForm.getQ(), searchForm.getCategories(), searchForm.getLimit());
    }

    public Iterable<Product> searchByNameOnly(String name, int limit){
        if (name.isEmpty()){
            return findAllDishes(limit);
        }
        String namePattern = "%" + name + "%";
        return dishRepository.findAllDishesWithName(namePattern, limit);
    }

    public Iterable<Product> searchByCategoriesOnly(List<Category> categories, int limit){
        String categoryIdList = generateCategoryIdListString(categories);
        Iterable<Product> result = dishRepository.findDishesByCategoryIdList(categoryIdList, limit);
        return result;
    }

    public Iterable<Product> searchByNameAndCategories(String name, List<Category> categories, int limit) {
        String namePattern = "%" + name + "%";
        String categoryIdList = generateCategoryIdListString(categories);
        return dishRepository.findDishesByNameAndCategoryIdList(namePattern, categoryIdList, limit);
    }

    public String generateCategoryIdListString(List<Category> categories) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            result.append(categories.get(i).getId().toString());
            if (i < categories.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

}
