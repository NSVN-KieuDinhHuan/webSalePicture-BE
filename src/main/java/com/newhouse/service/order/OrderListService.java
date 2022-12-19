package com.newhouse.service.order;


import com.newhouse.model.entity.OrderList;
import com.newhouse.repository.IOrderListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderListService implements IOrderListService {
    @Autowired
    IOrderListRepository orderListRepository;


    @Override
    public Iterable<OrderList> findAll() {
        return orderListRepository.findAll();
    }

    @Override
    public Optional<OrderList> findById(Long id) {
        return orderListRepository.findById(id);
    }

    @Override
    public OrderList save(OrderList order) {
        return orderListRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderListRepository.deleteById(id);
    }

}
