package com.newhouse.controller.order;

import com.newhouse.model.dto.order.OrderDto;
import com.newhouse.model.entity.Order;
import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.OrderGroup;
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

    @PostMapping
    public ResponseEntity<Order> saveOrderDetailList(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        List<Option> optionList=new ArrayList<>();
        for (int i = 0; i < orderDto.getProductOption().size(); i++) {
            Optional<Option> proOpt= optionService.findById( orderDto.getProductOption().get(i));
            proOpt.ifPresent(optionList::add);
        }
       Optional<OrderGroup> orderGroup= orderGroupService.findById(orderDto.getOrderGroupId());
        order.setDishId(orderDto.getDishId());
        order.setOptionList(optionList);
        order.setQuantity(orderDto.getQuantity());
        order.setOrderGroup(orderGroup.get());
        return new ResponseEntity<>(orderService.save(order),HttpStatus.BAD_REQUEST);
    }







}
