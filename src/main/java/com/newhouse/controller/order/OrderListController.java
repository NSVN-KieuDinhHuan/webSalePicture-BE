package com.newhouse.controller.order;


import com.newhouse.model.dto.order.OrderListDto;
import com.newhouse.model.entity.OrderDetail;
import com.newhouse.model.entity.OrderList;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.option.IProductOptService;
import com.newhouse.service.order.IOrderDetailService;
import com.newhouse.service.order.IOrderListService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ordersList")
public class OrderListController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderListService orderListService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductOptService productOptService;

    @PostMapping
    public ResponseEntity<OrderList> saveOrderList(@RequestBody OrderListDto orderListDto) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String name = principal.getName();
        User currentUser = userService.findByUsername(name).get();

        List<OrderDetail> OrderDetailList= new ArrayList<>();
        for (int i = 0; i <  orderListDto.getOrderDetailList().size(); i++) {
            Optional<OrderDetail>  orderDetail = orderDetailService.findById(orderListDto.getOrderDetailList().get(i));
            orderDetail.ifPresent(OrderDetailList::add);
        }

        OrderList orderList= new OrderList();
        orderList.setUser(currentUser);
        orderList.setCreateDate(orderListDto.getCreateDate());
        orderList.setOrderDetailList(OrderDetailList);
        orderList.setStatus(orderListDto.getStatus());
        return new ResponseEntity<>(orderListService.save(orderList),HttpStatus.BAD_REQUEST);
    }







}
