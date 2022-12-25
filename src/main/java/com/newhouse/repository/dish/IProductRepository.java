package com.newhouse.repository.dish;


import com.newhouse.model.entity.dish.Product;
import com.newhouse.model.entity.dish.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
//    Page<Product> findAll(Pageable pageable);
     @Query(value = "select product.* from product join product_categories dc on product.id = dc.product_id " +
        "where categories_id = :categoryId", nativeQuery = true)
     Iterable<Product> findDishesbyCategoryID(
        @Param(value = "categoryId") int categoryId);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    int countDishByCategoriesIsContaining(Category category);

    @Query(value = "select * from product d order by d.sold desc limit :top offset 0", nativeQuery = true)
    Iterable<Product> findTopPurchased(@Param("top") int top);

    Iterable<Product> findAllByCategoriesContaining(Category category);

    @Query(value = "select * from product limit :limit", nativeQuery = true)
    Iterable<Product> findAllDishes(@Param(value = "limit") int limit);

    @Query(value = "select * from product where name like :namePattern limit :limit", nativeQuery = true)
    Iterable<Product> findAllDishesWithName(@Param(value = "namePattern") String namePattern, @Param(value = "limit") int limit);


    @Query(value = "select product.* from dishes join product_categories dc on product.id = dc.product_id " +
            "where categories_id in (:categoryIdList) limit :limit offset 0", nativeQuery = true)
    Iterable<Product> findDishesByCategoryIdList(
            @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select product.* from product join product_categories dc on product.id = dc.dish_id " +
            "where categories_id in (:categoryIdList) and product.name like :namePattern  limit :limit offset 0", nativeQuery = true)
    Iterable<Product> findDishesByNameAndCategoryIdList(
            @Param(value = "namePattern") String namePattern, @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select distinct product.* " +
            "from product join product_categories d on product.id = d.dish_id " +
            "where d.categories_id in ( " +
            "    select dc.categories_id " +
            "    from product_categories dc " +
            "    where dc.product_id = :dishId) " +
            "and product.id != :dishId " +
            "order by product.sold desc " +
            "limit :limit", nativeQuery = true)
    Iterable<Product> findDishesWithSameCategoryWith(@Param(value = "dishId") Long dishId, @Param(value = "limit") int limit);
}
