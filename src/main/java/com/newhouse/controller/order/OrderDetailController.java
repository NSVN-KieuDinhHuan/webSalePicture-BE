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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orderdetail")
public class OrderDetailController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductOptService productOptService;

    @PostMapping
    public ResponseEntity<OrderDetail> saveOrderDetailList(@RequestBody OrderDetailDto OrderDetailDto) {
        OrderDetail orderDetail= new OrderDetail();
        List<ProductOption> optionList=new ArrayList<>();
        for (int i = 0; i < OrderDetailDto.getProductOption().size(); i++) {
            Optional<ProductOption> proOpt= productOptService.findById( OrderDetailDto.getProductOption().get(i));
            proOpt.ifPresent(optionList::add);
        }
        orderDetail.setDishId(OrderDetailDto.getDishId());
        orderDetail.setOptionList(optionList);
        orderDetail.setQuantity(OrderDetailDto.getQuantity());
        return new ResponseEntity<>(orderDetailService.save(orderDetail),HttpStatus.BAD_REQUEST);
    }







}
