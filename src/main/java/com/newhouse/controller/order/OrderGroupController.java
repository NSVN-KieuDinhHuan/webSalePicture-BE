package com.newhouse.controller.order;


import com.newhouse.model.dto.order.OrderDto;

import com.newhouse.model.dto.order.OrderGroupDto;
import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.Order;
import com.newhouse.model.entity.OrderGroup;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.option.IOptionService;
import com.newhouse.service.order.IOrderService;
import com.newhouse.service.order.IOrderGroupService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order-group")
public class OrderGroupController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderGroupService orderListService;

    @Autowired
    IOrderService orderDetailService;

    @Autowired
    IOptionService productOptService;

    @PostMapping
    public ResponseEntity<OrderGroup> saveOrderGroup(@RequestBody OrderGroupDto orderGroupDto) {
        Optional<User> currentUser = userService.findByPhone(orderGroupDto.getUserPhone());
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setUser(currentUser.get());
        orderGroup.setCreateDate(orderGroupDto.getCreateDate());
        orderGroup.setStatus(orderGroupDto.getStatus());
        return new ResponseEntity<>(orderListService.save(orderGroup),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        Iterable<OrderGroup> OrderGroupAll = orderListService.findAll();
        return new ResponseEntity<>(OrderGroupAll, HttpStatus.OK);
    }

    @PostMapping ("/{id}")
    public ResponseEntity<OrderGroup> update(@PathVariable Long id, @ModelAttribute OrderGroupDto orderGroupDto) {
        Optional<OrderGroup> orderGroup = orderListService.findById(id);
        OrderGroup opt =orderGroup.get();
        if(!orderGroup.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        opt.setStatus(orderGroupDto.getStatus());
        return new ResponseEntity<>(orderListService.save(opt), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOptionList(@PathVariable Long id){
        Optional<OrderGroup> ProductOption = orderListService.findById(id);
        return new ResponseEntity<>(ProductOption, HttpStatus.OK);
    }
}
