package com.newhouse.repository;

import com.newhouse.model.entity.Cart;
import com.newhouse.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {

}
