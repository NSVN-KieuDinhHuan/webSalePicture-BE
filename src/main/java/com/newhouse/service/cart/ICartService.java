package com.newhouse.service.cart;

import com.newhouse.model.dto.cart.CartDetailDto;
import com.newhouse.model.dto.cart.CartDto;
import com.newhouse.model.entity.Cart;
import com.newhouse.model.entity.CartDetail;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.IGeneralService;

import java.util.List;

public interface ICartService extends IGeneralService<Cart> {
    Cart addDishToCart( Long cartId,CartDetail cartDetail);
}
