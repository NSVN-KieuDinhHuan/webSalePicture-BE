package com.newhouse.service.cart;

import com.newhouse.model.dto.cart.CartDetailDto;
import com.newhouse.model.dto.cart.CartDto;
import com.newhouse.model.dto.order.OrderListDto;
import com.newhouse.model.entity.*;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.user.User;
import com.newhouse.repository.ICartRepository;
import com.newhouse.service.cart_detail.ICartDetailService;
import com.newhouse.service.dish.IDishService;
import com.newhouse.service.order.IOrderListService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;

    @Autowired
    ICartDetailService cartDetailService;

    @Autowired
    IOrderListService orderService;


    @Autowired
    IDishService dishService;

    @Autowired
    IUserService userService;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }


    @Override
    public Cart addDishToCart(Long cartId ,CartDetail cartDetail) {
        Cart cart = findById(cartId).get();
        List<CartDetail> CartDetailList=cart.getCartDetailList();
        CartDetailList.add(cartDetail);
        cart.setCartDetailList(CartDetailList);
        cartRepository.save(cart);
        return cart;
    }
}
