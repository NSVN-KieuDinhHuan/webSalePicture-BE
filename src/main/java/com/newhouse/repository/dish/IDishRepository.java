package com.newhouse.repository.dish;


import com.newhouse.model.entity.dish.category.Category;
import com.newhouse.model.entity.dish.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends PagingAndSortingRepository<Dish, Long> {
//    Page<Dish> findAll(Pageable pageable);
     @Query(value = "select dishes.* from dishes join dishes_categories dc on dishes.id = dc.dish_id " +
        "where categories_id = :categoryId", nativeQuery = true)
     Iterable<Dish> findDishesbyCategoryID(
        @Param(value = "categoryId") int categoryId);

    Page<Dish> findAllByNameContaining(String name, Pageable pageable);

    int countDishByCategoriesIsContaining(Category category);

    @Query(value = "select * from dishes d order by d.sold desc limit :top offset 0", nativeQuery = true)
    Iterable<Dish> findTopPurchased(@Param("top") int top);

    Iterable<Dish> findAllByCategoriesContaining(Category category);

    @Query(value = "select * from dishes limit :limit", nativeQuery = true)
    Iterable<Dish> findAllDishes(@Param(value = "limit") int limit);

    @Query(value = "select * from dishes where name like :namePattern limit :limit", nativeQuery = true)
    Iterable<Dish> findAllDishesWithName(@Param(value = "namePattern") String namePattern, @Param(value = "limit") int limit);


    @Query(value = "select dishes.* from dishes join dishes_categories dc on dishes.id = dc.dish_id " +
            "where categories_id in (:categoryIdList) limit :limit offset 0", nativeQuery = true)
    Iterable<Dish> findDishesByCategoryIdList(
            @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select dishes.* from dishes join dishes_categories dc on dishes.id = dc.dish_id " +
            "where categories_id in (:categoryIdList) and dishes.name like :namePattern  limit :limit offset 0", nativeQuery = true)
    Iterable<Dish> findDishesByNameAndCategoryIdList(
            @Param(value = "namePattern") String namePattern, @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select distinct dishes.* " +
            "from dishes join dishes_categories d on dishes.id = d.dish_id " +
            "where d.categories_id in ( " +
            "    select dc.categories_id " +
            "    from dishes_categories dc " +
            "    where dc.dish_id = :dishId) " +
            "and dishes.id != :dishId " +
            "order by dishes.sold desc " +
            "limit :limit", nativeQuery = true)
    Iterable<Dish> findDishesWithSameCategoryWith(@Param(value = "dishId") Long dishId, @Param(value = "limit") int limit);
}
