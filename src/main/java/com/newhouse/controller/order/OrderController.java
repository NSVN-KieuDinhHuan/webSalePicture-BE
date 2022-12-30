package com.newhouse.controller.order;

import com.newhouse.model.dto.order.OrderDto;
import com.newhouse.model.entity.CartGroup;
import com.newhouse.model.entity.Order;
import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.OrderGroup;
import com.newhouse.service.cart.ICartService;
import com.newhouse.service.option.IOptionService;
import com.newhouse.service.order.IOrderGroupService;
import com.newhouse.service.order.IOrderService;
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
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderGroupService orderGroupService;
    @Autowired
    IOptionService optionService;

    @Autowired
    ICartService cartService;

    @PostMapping
    public ResponseEntity<?> saveOrderDetailList(@RequestBody OrderDto[] orderDto) {
        List<Order> orders=new ArrayList<>();
        for (int i = 0; i < orderDto.length; i++) {
            Order order = new Order();
            List<Option> optionList=orderDto[i].getOptionList();
            OrderGroup orderGroup= orderDto[i].getOrderGroup();
            order.setDishId(orderDto[i].getDishId());
            order.setOptionList(optionList);
            order.setQuantity(orderDto[i].getQuantity());
            order.setOrderGroup(orderGroup);
            orders.add(order);
            orderService.save(order);

        }

        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        Iterable<Order> orderAll = orderService.findAll();
        return new ResponseEntity<>(orderAll, HttpStatus.OK);
    }






}
