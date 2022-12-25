package com.newhouse.service.cart;

import com.newhouse.model.entity.Cart;
import com.newhouse.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;

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
        Cart cart01=cartRepository.save(cart);
        return cart01;
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getCartDetailByCartID(Long id) {
         List<Cart> cartDetailList=cartRepository.getAllByCartID(id);
        return cartDetailList;
    }

    @Override
    public void deleteCartByCartGroupId(int id) {
        cartRepository.deleteCartByCartGroupId(id);
    }
}
