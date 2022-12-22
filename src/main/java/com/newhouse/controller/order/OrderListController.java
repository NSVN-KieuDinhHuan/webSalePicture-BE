package com.newhouse.controller.order;


import com.newhouse.model.dto.order.OrderDetailDto;
import com.newhouse.model.dto.order.OrderListDto;
import com.newhouse.model.entity.OrderDetail;
import com.newhouse.model.entity.OrderList;
import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.option.IProductOptService;
import com.newhouse.service.order.IOrderDetailService;
import com.newhouse.service.order.IOrderListService;
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
        Optional<User> currentUser = userService.findByPhone(orderListDto.getUserPhone());

        List<OrderDetail> OrderDetailList= new ArrayList<>();
        List<OrderDetailDto> orderDetailDto= orderListDto.getOrderDetailList();
        for (int i = 0; i <  orderDetailDto.size(); i++) {
            OrderDetail orderDetail= new OrderDetail();
            List<ProductOption> optionList=new ArrayList<>();
            for (int j = 0; j < orderDetailDto.get(i).getProductOption().size(); j++) {
                Optional<ProductOption> proOpt= productOptService.findById(orderDetailDto.get(i).getProductOption().get(i));
                proOpt.ifPresent(optionList::add);
            }
            orderDetail.setDishId(orderDetailDto.get(i).getDishId());
            orderDetail.setOptionList(optionList);
            orderDetail.setQuantity(orderDetailDto.get(i).getQuantity());
            orderDetailService.save(orderDetail);
            OrderDetailList.add(orderDetail);
        }
        OrderList orderList= new OrderList();
        orderList.setUser(currentUser.get());
        orderList.setCreateDate(orderListDto.getCreateDate());
        orderList.setOrderDetailList(OrderDetailList);
        orderList.setStatus(orderListDto.getStatus());
        return new ResponseEntity<>(orderListService.save(orderList),HttpStatus.OK);
    }


}
