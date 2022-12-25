package com.newhouse.service.order;


import com.newhouse.model.entity.OrderGroup;
import com.newhouse.repository.IOrderGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderGroupService implements IOrderGroupService {
    @Autowired
    IOrderGroupRepository orderListRepository;


    @Override
    public Iterable<OrderGroup> findAll() {
        return orderListRepository.findAll();
    }

    @Override
    public Optional<OrderGroup> findById(Long id) {
        return orderListRepository.findById(id);
    }

    @Override
    public OrderGroup save(OrderGroup order) {
        return orderListRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderListRepository.deleteById(id);
    }

}
