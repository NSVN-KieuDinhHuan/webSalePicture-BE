package com.newhouse.service.cart;

import com.newhouse.model.entity.Cart;
import com.newhouse.service.IGeneralService;

import java.util.List;

public interface ICartService extends IGeneralService<Cart> {
       List<Cart> getCartDetailByCartID(Long id);

       void deleteCartByCartGroupId(int id);
}
