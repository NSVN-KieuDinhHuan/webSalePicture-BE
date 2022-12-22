package com.newhouse.repository;


import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from dishes limit :limit", nativeQuery = true)
    Iterable<Dish> findAllDishes(@Param(value = "limit") int limit);

    Optional<User> findByUsername(String username);


    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param(value = "email") String  email);

    @Query(value = "select * from users where phone = :phone", nativeQuery = true)
    Optional<User> findByPhone(@Param(value = "phone") String  phone);
}
