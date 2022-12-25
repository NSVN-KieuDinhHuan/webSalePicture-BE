package com.newhouse.service.cartgroup;

import com.newhouse.model.entity.*;
import com.newhouse.repository.ICartGroupRepository;
import com.newhouse.service.cart.ICartService;
import com.newhouse.service.product.IProductService;
import com.newhouse.service.order.IOrderGroupService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartGroupService implements ICartGroupService {
    @Autowired
    ICartGroupRepository cartGroupRepository;

    @Autowired
    ICartService cartDetailService;

    @Autowired
    IOrderGroupService orderService;


    @Autowired
    IProductService dishService;

    @Autowired
    IUserService userService;

    @Override
    public Iterable<CartGroup> findAll() {
        return cartGroupRepository.findAll();
    }

    @Override
    public Optional<CartGroup> findById(Long id) {
        return cartGroupRepository.findById(id);
    }



    @Override
    public CartGroup save(CartGroup cart) {
        return cartGroupRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartGroupRepository.deleteById(id);
    }



}
