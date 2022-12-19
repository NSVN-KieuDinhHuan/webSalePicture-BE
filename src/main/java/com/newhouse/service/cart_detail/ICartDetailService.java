package com.newhouse.service.cart_detail;

import com.newhouse.model.entity.Cart;
import com.newhouse.model.entity.CartDetail;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {
       List<CartDetail> getCartDetailByCartID(Long id);
}
