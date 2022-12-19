package com.newhouse.service.cart_detail;

import com.newhouse.model.entity.Cart;
import com.newhouse.model.entity.CartDetail;
import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    ICartDetailRepository cartDetailRepository;

    @Override
    public Iterable<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        CartDetail cartDetail1=cartDetailRepository.save(cartDetail);
        return cartDetail1;
    }

    @Override
    public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public List<CartDetail> getCartDetailByCartID(Long id) {
         List<CartDetail> cartDetailList=cartDetailRepository.getAllByCartID(id);
        return cartDetailList;
    }
}
